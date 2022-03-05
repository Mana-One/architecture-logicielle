package fr.al_cc2.web.project;

import fr.al_cc2.application.project.create.CreateProject;
import fr.al_cc2.domain.model.Owner;
import fr.al_cc2.domain.model.ProjectId;
import fr.al_cc2.kernel.command.CommandBus;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@RestController
public class ProjectController {
    private final CommandBus commandBus;

    public ProjectController(CommandBus commandBus) {
        this.commandBus = commandBus;
    }

    @PostMapping(path = "/projects", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> create(@RequestBody @Valid ProjectRequest request) {
        CreateProject createProject = new CreateProject(request.name, new Owner(request.owner) , request.status, request.startDate, request.endDate);
        ProjectId projectId = commandBus.send(createProject);
        return ResponseEntity.created(URI.create("/projects/" + projectId.getValue())).build();
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
}
