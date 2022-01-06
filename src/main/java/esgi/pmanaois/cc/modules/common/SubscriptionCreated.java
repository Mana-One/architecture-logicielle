package esgi.pmanaois.cc.modules.common;

import esgi.pmanaois.cc.kernel.Event;

final public class SubscriptionCreated implements Event {
    final private String subscriptionId;
    final private String subscriberId;

    public SubscriptionCreated(String subscriptionId, String subscriberId) {
        this.subscriptionId = subscriptionId;
        this.subscriberId = subscriberId;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public String getSubscriberId() {
        return subscriberId;
    }
}
