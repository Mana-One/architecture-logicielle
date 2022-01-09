package esgi.pmanaois.cc.modules.subscriptions.domain;

import java.time.ZonedDateTime;
import java.util.Objects;

final public class SubscriptionInitialized implements SubscriptionEvent {
    final private double price;
    final private String currency;
    final private String paymentMethodId;
    final private String subscriberId;
    final private ZonedDateTime started;
    final private ZonedDateTime dueDate;

    public SubscriptionInitialized(double price, String currency, String paymentMethodId, String subscriberId, ZonedDateTime started, ZonedDateTime dueDate) {
        this.price = price;
        this.currency = currency;
        this.paymentMethodId = paymentMethodId;
        this.subscriberId = subscriberId;
        this.started = started;
        this.dueDate = dueDate;
    }

    public double getPrice() {
        return price;
    }

    public String getCurrency() {
        return currency;
    }

    public String getPaymentMethodId() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SubscriptionInitialized)) return false;
        SubscriptionInitialized that = (SubscriptionInitialized) o;
        return Double.compare(that.price, price) == 0 && Objects.equals(currency, that.currency) && Objects.equals(paymentMethodId, that.paymentMethodId) && Objects.equals(subscriberId, that.subscriberId) && Objects.equals(started, that.started) && Objects.equals(dueDate, that.dueDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(price, currency, paymentMethodId, subscriberId, started, dueDate);
    }
}
