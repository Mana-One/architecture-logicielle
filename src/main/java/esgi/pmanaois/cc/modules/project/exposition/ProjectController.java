package esgi.pmanaois.cc.modules.project.exposition;

import esgi.pmanaois.cc.kernel.CommandBus;
import esgi.pmanaois.cc.kernel.QueryBus;
import esgi.pmanaois.cc.modules.project.application.assignworker.AssignWorker;
import esgi.pmanaois.cc.modules.project.application.close.CloseProject;
import esgi.pmanaois.cc.modules.project.application.create.RegisterProject;
import esgi.pmanaois.cc.modules.project.application.list.ListProjects;
import esgi.pmanaois.cc.modules.project.domain.InvalidProjectState;
import esgi.pmanaois.cc.modules.project.domain.NoSuchProject;
import esgi.pmanaois.cc.modules.project.domain.model.Project;
import esgi.pmanaois.cc.modules.project.domain.model.ProjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
public class ProjectController {
    private final CommandBus commandBus;
    private final QueryBus queryBus;

    public ProjectController(CommandBus commandBus, QueryBus queryBus) {
        this.commandBus = Objects.requireNonNull(commandBus);
        this.queryBus = Objects.requireNonNull(queryBus);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "/projects", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProjectResponse> create(@RequestBody @Valid ProjectRequest request) {
        RegisterProject registerProject = new RegisterProject(request.name, request.owner, request.requiredSkills);
        Project project = commandBus.send(registerProject);
        return ResponseEntity.ok(new ProjectResponse(
            project.getId().getValue().toString(), 
            project.getName(), 
            project.getOwner().getValue().toString(), 
            project.getStatus().toString(), 
            project.getRequiredSkills(),
            project.getWorkers().stream().map(w -> w.getValue().toString()).collect(Collectors.toList()),
            project.getStartDate(), 
            project.getEndDate()
        ));
    }

    @GetMapping("/projects")
    public ResponseEntity<ProjectsResponse> list() {
        List<Project> projects = this.queryBus.send(new ListProjects());
        List<ProjectResponse> response = projects.stream()
            .map(p -> new ProjectResponse(
                p.getId().getValue().toString(), 
                p.getName(), 
                p.getOwner().getValue().toString(), 
                p.getStatus().toString(), 
                p.getRequiredSkills(),
                p.getWorkers().stream().map(w -> w.getValue().toString()).collect(Collectors.toList()),
                p.getStartDate(), 
                p.getEndDate())
            ).collect(Collectors.toList()
        );
        return ResponseEntity.ok(new ProjectsResponse(response));
    }

    @PutMapping(path = "/projects/{projectId}/assign-worker",  consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> assingWorker(
        @PathVariable("projectId") String projectId,
        @RequestBody @Valid AssignWorkerRequest request
    ) {
        AssignWorker assignWorker = new AssignWorker(projectId, request.worker);
        commandBus.send(assignWorker);
        return null;
    }

    @PutMapping(path = "/projects/{projectId}/close")
    public ResponseEntity<Void> close(@PathVariable("projectId") String projectId) {
        CloseProject closeProject = new CloseProject(projectId);
        commandBus.send(closeProject);
        return ResponseEntity.ok().build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidProjectState.class)
    public Map<String, String> handleInvalidProjectException(InvalidProjectState invalidProjectState) {
        final Map<String, String> error = new HashMap<>();
        error.put("message", invalidProjectState.getMessage());
        return error;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchProject.class)
    public Map<String, String> handleNoSuchProjectException(NoSuchProject ex) {
        final Map<String, String> error = new HashMap<>();
        error.put("message", ex.getMessage());
        return error;
    }
}
