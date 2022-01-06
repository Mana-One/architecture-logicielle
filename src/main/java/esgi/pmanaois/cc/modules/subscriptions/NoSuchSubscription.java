package esgi.pmanaois.cc.modules.subscriptions;

import esgi.pmanaois.cc.modules.subscriptions.domain.SubscriptionId;

public class NoSuchSubscription extends RuntimeException {
    public NoSuchSubscription(String message) {
        super(message);
    }

    public static NoSuchSubscription withId(SubscriptionId id) {
        return new NoSuchSubscription("No user with id '" + id.getValue().toString() + "'");
    }
}
