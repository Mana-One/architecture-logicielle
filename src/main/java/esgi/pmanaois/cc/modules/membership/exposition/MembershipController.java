package esgi.pmanaois.cc.modules.membership.exposition;

import esgi.pmanaois.cc.modules.membership.InvalidUserState;
import esgi.pmanaois.cc.modules.membership.application.RegisterUser;
import esgi.pmanaois.cc.modules.membership.application.RegisterUserHandler;
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
public class MembershipController {
    final private RegisterUserHandler registerUserHandler;

    public MembershipController(RegisterUserHandler registerUserHandler) {
        this.registerUserHandler = Objects.requireNonNull(registerUserHandler);
    }

    @PostMapping(path = "/membership", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> registerUser(@RequestBody @Valid CreateUserRequest request) {
        RegisterUser registerUser = new RegisterUser(
                request.firstName,
                request.lastName,
                request.email,
                request.role,
                request.paymentMethodId);
        this.registerUserHandler.handle(registerUser);

        return null;
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
    public String handleInvalidUserException(InvalidUserState ex) {
        return ex.getMessage();
    }

}