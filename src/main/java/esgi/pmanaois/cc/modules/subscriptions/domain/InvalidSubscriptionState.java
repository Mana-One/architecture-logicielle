package esgi.pmanaois.cc.modules.subscriptions.domain;

public class InvalidSubscriptionState extends IllegalStateException {
    public InvalidSubscriptionState(String message) {
        super(message);
    }
}
