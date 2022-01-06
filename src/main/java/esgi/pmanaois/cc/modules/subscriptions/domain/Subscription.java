package esgi.pmanaois.cc.modules.subscriptions.domain;

import esgi.pmanaois.cc.kernel.Entity;
import esgi.pmanaois.cc.modules.common.PaymentMethodId;

import java.time.ZonedDateTime;
import java.util.Objects;

public class Subscription implements Entity<SubscriptionId> {
    final private SubscriptionId id;
    final private Price price;
    final private PaymentMethodId paymentMethodId;
    final private String subscriberId;
    private ZonedDateTime started;
    private ZonedDateTime dueDate;

    private Subscription(SubscriptionId id, Price price, PaymentMethodId paymentMethodId, String subscriberId, ZonedDateTime started, ZonedDateTime dueDate) {
        this.id = Objects.requireNonNull(id);
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

    public static Subscription create(Price price, PaymentMethodId paymentMethodId, String subscriberId, ZonedDateTime started) {
        return new Subscription(
                SubscriptionId.generate(),
                price,
                paymentMethodId,
                subscriberId,
                started,
                started.plusMonths(1)
        );
    }

    public static Subscription of(SubscriptionId id, Price price, PaymentMethodId paymentMethodId, String subscriberId, ZonedDateTime started, ZonedDateTime dueDate) {
        return new Subscription(id, price, paymentMethodId, subscriberId, started, dueDate);
    }

    @Override
    public SubscriptionId getId() {
        return this.id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Subscription)) return false;
        Subscription that = (Subscription) o;
        return Objects.equals(id, that.id) && Objects.equals(price, that.price) && Objects.equals(paymentMethodId, that.paymentMethodId) && Objects.equals(subscriberId, that.subscriberId) && Objects.equals(started, that.started) && Objects.equals(dueDate, that.dueDate);
    }
}
