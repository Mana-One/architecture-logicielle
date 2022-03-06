package esgi.pmanaois.cc.modules.membership.exposition;

import esgi.pmanaois.cc.kernel.CommandBus;
import esgi.pmanaois.cc.kernel.QueryBus;
import esgi.pmanaois.cc.modules.membership.domain.InvalidUserState;
import esgi.pmanaois.cc.modules.membership.domain.User;
import esgi.pmanaois.cc.modules.membership.application.ListUsers;
import esgi.pmanaois.cc.modules.membership.application.RegisterUser;
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
public class MembershipController {
    final private CommandBus commandBus;
    final private QueryBus queryBus;

    public MembershipController(CommandBus commandBus, QueryBus queryBus) {
        this.commandBus = Objects.requireNonNull(commandBus);
        this.queryBus = Objects.requireNonNull(queryBus);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "/membership", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> registerUser(@RequestBody @Valid CreateUserRequest request) {
        RegisterUser registerUser = new RegisterUser(
                request.firstName,
                request.lastName,
                request.email,
                request.role,
                request.paymentMethodId);
        this.commandBus.send(registerUser);

        return null;
    }

    @GetMapping(path = "/membership")
    public ResponseEntity<UsersResponse> listUsers() {
        final List<User> users = this.queryBus.send(new ListUsers());
        return ResponseEntity.ok(new UsersResponse(users.stream()
            .map(u -> new UserResponse(
                u.getId().getValue().toString(), 
                u.getName().getFirst(), 
                u.getName().getLast(), 
                u.getRole().toString(), 
                u.getStatus().toString()
            )).collect(Collectors.toList())
        ));
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
    @ExceptionHandler(InvalidUserState.class)
    public Map<String, String> handleInvalidUserException(InvalidUserState ex) {
        final Map<String, String> error = new HashMap<>();
        error.put("message", ex.getMessage());
        return error;
    }

}
