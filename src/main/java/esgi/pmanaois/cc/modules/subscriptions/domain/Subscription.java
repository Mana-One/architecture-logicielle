package esgi.pmanaois.cc.modules.subscriptions.domain;

import esgi.pmanaois.cc.kernel.Entity;
import esgi.pmanaois.cc.modules.common.PaymentMethodId;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

final public class Subscription implements Entity<SubscriptionId> {
    final private SubscriptionId id;
    private Price price;
    private PaymentMethodId paymentMethodId;
    private String subscriberId;
    private ZonedDateTime started;
    private ZonedDateTime dueDate;
    final private List<SubscriptionEvent> events;


    private Subscription(SubscriptionId id, List<SubscriptionEvent> events) {
        this.id = Objects.requireNonNull(id);
        this.events = Objects.requireNonNull(events);
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

    public List<SubscriptionEvent> getEvents() { return this.events; }

    public void setForNextMonth(ZonedDateTime newStarted) {
        this.events.add(new SubscriptionContinued(newStarted, newStarted.plusMonths(1)));
    }

    public static Subscription init(SubscriptionId id) {
        return new Subscription(id, new ArrayList<SubscriptionEvent>());
    }

    public static Subscription create(Price price, PaymentMethodId paymentMethodId, String subscriberId, ZonedDateTime started) {
        List<SubscriptionEvent> events = new ArrayList<>();
        events.add(new SubscriptionInitialized(
                price.getValue(),
                price.getCurrency(),
                paymentMethodId.getValue(),
                subscriberId,
                started,
                started.plusMonths(1)));

        return new Subscription(SubscriptionId.generate(), events);
    }

    // needed for stubbing
    public static Subscription of(SubscriptionId id, Price price, PaymentMethodId paymentMethodId, String subscriberId, ZonedDateTime started, ZonedDateTime dueDate) {
        Subscription subscription = new Subscription(id, new ArrayList<SubscriptionEvent>());
        subscription.price = price;
        subscription.paymentMethodId = paymentMethodId;
        subscription.subscriberId = subscriberId;
        subscription.started = started;
        subscription.dueDate = dueDate;
        return subscription;
    }

    private void apply(SubscriptionInitialized event) {
        this.price = Price.of(event.getPrice(), event.getCurrency());
        this.paymentMethodId = PaymentMethodId.of(event.getPaymentMethodId());
        this.subscriberId = event.getSubscriberId();
        this.started = event.getStarted();
        this.dueDate = event.getDueDate();
    }

    private void apply(SubscriptionContinued event) {
        this.started = event.getStarted();
        this.dueDate = event.getDueDate();
    }

    public void replay(List<SubscriptionEvent> events) {
        for (SubscriptionEvent event : events) {
            if (event instanceof SubscriptionInitialized) {
                this.apply((SubscriptionInitialized) event);
            }

            if (event instanceof SubscriptionContinued) {
                this.apply((SubscriptionContinued) event);
            }
        }
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
        return Objects.equals(id, that.id);
    }
}
