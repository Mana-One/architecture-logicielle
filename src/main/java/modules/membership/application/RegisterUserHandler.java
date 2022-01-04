package modules.membership.application;

import esgi.kernel.CommandHandler;
import modules.membership.domain.EmailValidationEngine;
import modules.membership.domain.User;
import modules.membership.domain.Users;

import java.util.Objects;

public class RegisterUserHandler implements CommandHandler<RegisterUser, Void> {
    final private Users users;
    final private EmailValidationEngine emailValidationEngine;

    public RegisterUserHandler(Users users, EmailValidationEngine emailValidationEngine) {
        this.users = Objects.requireNonNull(users);
        this.emailValidationEngine = Objects.requireNonNull(emailValidationEngine);
    }

    @Override
    public Void handle(RegisterUser command) {
        final User user = User.create(
                command.firstName,
                command.lastName,
                command.email,
                command.role,
                command.paymentMethodId,
                this.emailValidationEngine);

        this.users.save(user);
        return null;
    }
}
