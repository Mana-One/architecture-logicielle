package esgi.pmanaois.cc.modules.membership.application;

import esgi.pmanaois.cc.kernel.CommandHandler;
import esgi.pmanaois.cc.kernel.Event;
import esgi.pmanaois.cc.kernel.EventDispatcher;
import esgi.pmanaois.cc.modules.common.UserRegistered;
import esgi.pmanaois.cc.modules.membership.domain.User;
import esgi.pmanaois.cc.modules.membership.domain.Users;

import java.util.Objects;

final public class RegisterUserHandler implements CommandHandler<RegisterUser, Void> {
    final private Users users;
    final private UserService userService;
    final private EventDispatcher eventDispatcher;

    public RegisterUserHandler(
            Users users,
            UserService userService,
            EventDispatcher<Event> eventDispatcher
    ) {
        this.users = Objects.requireNonNull(users);
        this.userService = Objects.requireNonNull(userService);
        this.eventDispatcher = Objects.requireNonNull(eventDispatcher);
    }

    @Override
    public Void handle(RegisterUser command) {
        final User user = this.userService.create(
                command.firstName,
                command.lastName,
                command.email,
                command.role,
                command.paymentMethodId);

        this.users.save(user);
        this.eventDispatcher.dispatch(new UserRegistered(
                user.getId().getValue().toString(),
                command.role,
                command.paymentMethodId));
        return null;
    }
}
