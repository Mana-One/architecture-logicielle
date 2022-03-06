package esgi.pmanaois.cc.modules.project.exposition;

import esgi.pmanaois.cc.kernel.CommandBus;
import esgi.pmanaois.cc.modules.project.application.close.CloseProject;
import esgi.pmanaois.cc.modules.project.application.create.RegisterProject;
import esgi.pmanaois.cc.modules.project.domain.InvalidProjectState;
import esgi.pmanaois.cc.modules.project.domain.model.Owner;
import esgi.pmanaois.cc.modules.project.domain.model.ProjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
public class ProjectController {
    private final CommandBus commandBus;

    public ProjectController(CommandBus commandBus) {
        this.commandBus = Objects.requireNonNull(commandBus);
    }

    @PostMapping(path = "/projects", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> create(@RequestBody @Valid ProjectRequest request) {
        RegisterProject registerProject = new RegisterProject(request.name, new Owner(request.owner), request.status, request.startDate, request.endDate);
        commandBus.send(registerProject);
        return null;
    }

    @PostMapping(path = "/projects/close", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> close(@RequestBody @Valid CloseProjectRequest request) {
        CloseProject closeProject = new CloseProject(ProjectId.fromString(request.projectId));
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
}
