package esgi.pmanaois.cc.modules.subscriptions.domain;

import esgi.pmanaois.cc.kernel.Entity;
import esgi.pmanaois.cc.modules.common.PaymentMethodId;

import java.time.ZonedDateTime;
import java.util.Objects;

public class Subscription implements Entity<SubscriptionId> {
    final private Price price;
    private PaymentMethodId paymentMethodId;
    private String subscriberId;
    private ZonedDateTime started;
    private ZonedDateTime dueDate;

    private Subscription(Price price, PaymentMethodId paymentMethodId, String subscriberId, ZonedDateTime started, ZonedDateTime dueDate) {
        this.price = Objects.requireNonNull(price);
        this.paymentMethodId = Objects.requireNonNull(paymentMethodId);
        this.subscriberId = Objects.requireNonNull(subscriberId);
        this.started = Objects.requireNonNull(started);
        this.dueDate = Objects.requireNonNull(dueDate);
    }

    public Price getPrice() {
        return price;
    }

    public PaymentMethodId getPaymentMethodId() {
        return paymentMethodId;
    }

    public String getSubscriberId() {
        return subscriberId;
    }

    public ZonedDateTime getStarted() {
        return started;
    }

    public ZonedDateTime getDueDate() {
        return dueDate;
    }

    public static Subscription of(Price price, PaymentMethodId paymentMethodId, String subscriberId, ZonedDateTime started, ZonedDateTime dueDate) {
        return new Subscription(price, paymentMethodId, subscriberId, started, dueDate);
    }

    @Override
    public SubscriptionId getId() {
        return null;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Subscription)) {
            return false;
        }
        Subscription subscription = (Subscription) obj;
        return this.getId().equals(subscription.getId());
    }
}
