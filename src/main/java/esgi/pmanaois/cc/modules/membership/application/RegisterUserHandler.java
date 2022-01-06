package esgi.pmanaois.cc.modules.membership.application;

import esgi.pmanaois.cc.kernel.CommandHandler;
import esgi.pmanaois.cc.kernel.Event;
import esgi.pmanaois.cc.kernel.EventDispatcher;
import esgi.pmanaois.cc.modules.common.UserRegistered;
import esgi.pmanaois.cc.modules.membership.domain.EmailValidationEngine;
import esgi.pmanaois.cc.modules.membership.domain.User;
import esgi.pmanaois.cc.modules.membership.domain.Users;

import java.util.Objects;

public class RegisterUserHandler implements CommandHandler<RegisterUser, Void> {
    final private Users users;
    final private EmailValidationEngine emailValidationEngine;
    final private EventDispatcher eventDispatcher;

    public RegisterUserHandler(
            Users users,
            EmailValidationEngine emailValidationEngine,
            EventDispatcher<Event> eventDispatcher
    ) {
        this.users = Objects.requireNonNull(users);
        this.emailValidationEngine = Objects.requireNonNull(emailValidationEngine);
        this.eventDispatcher = Objects.requireNonNull(eventDispatcher);
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
        this.eventDispatcher.dispatch(new UserRegistered(
                user.getId().getValue().toString(),
                user.getRole().getName(),
                user.getPaymentMethodId().getValue()));
        return null;
    }
}
