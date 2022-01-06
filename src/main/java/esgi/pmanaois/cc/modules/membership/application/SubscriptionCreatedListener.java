package esgi.pmanaois.cc.modules.membership.application;

import esgi.pmanaois.cc.kernel.EventListener;
import esgi.pmanaois.cc.modules.common.SubscriptionCreated;
import esgi.pmanaois.cc.modules.membership.InvalidUserState;
import esgi.pmanaois.cc.modules.membership.NoSuchUser;
import esgi.pmanaois.cc.modules.membership.domain.User;
import esgi.pmanaois.cc.modules.membership.domain.UserId;
import esgi.pmanaois.cc.modules.membership.domain.Users;

import java.util.Optional;

final public class SubscriptionCreatedListener implements EventListener<SubscriptionCreated> {
    final private Users users;

    public SubscriptionCreatedListener(Users users) {
        this.users = users;
    }

    @Override
    public void listenTo(SubscriptionCreated event) {
        try {
            Optional<UserId> optUserId = UserId.fromString(event.getSubscriberId());
            if (optUserId.isEmpty()) {
                throw new InvalidUserState("Invalid user id from event.");
            }

            Optional<User> optUser = this.users.findById(optUserId.get());
            if (optUser.isEmpty()) {
                throw NoSuchUser.withId(optUserId.get());
            }

            User user = optUser.get();
            user.verify();
            this.users.save(user);
            System.out.println("User verified");

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
