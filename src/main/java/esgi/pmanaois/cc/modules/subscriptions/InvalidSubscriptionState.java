package esgi.pmanaois.cc.modules.subscriptions;

public class InvalidSubscriptionState extends IllegalStateException {
    public InvalidSubscriptionState(String message) {
        super(message);
    }
}
