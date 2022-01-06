package esgi.pmanaois.cc.modules.subscriptions.application;

import esgi.pmanaois.cc.kernel.Clock;
import esgi.pmanaois.cc.kernel.EventDispatcher;
import esgi.pmanaois.cc.kernel.EventListener;
import esgi.pmanaois.cc.modules.common.PaymentMethodId;
import esgi.pmanaois.cc.modules.common.SubscriptionCreated;
import esgi.pmanaois.cc.modules.common.UserRegistered;
import esgi.pmanaois.cc.modules.subscriptions.domain.Price;
import esgi.pmanaois.cc.modules.subscriptions.domain.Subscription;
import esgi.pmanaois.cc.modules.subscriptions.domain.Subscriptions;

import java.time.ZonedDateTime;
import java.util.Objects;

final public class UserRegisteredListener implements EventListener<UserRegistered> {
    final private static Price REGISTRATION_PRICE = Price.of(60.0, "EUR");
    final private static Price SUBSCRIPTION_PRICE = Price.of(100.0, "EUR");

    final private Clock clock;
    final private EventDispatcher dispatcher;
    final private PaymentGateway paymentGateway;
    final private Subscriptions subscriptions;

    public UserRegisteredListener(Clock clock, EventDispatcher dispatcher, PaymentGateway paymentGateway, Subscriptions subscriptions) {
        this.clock = Objects.requireNonNull(clock);
        this.dispatcher = Objects.requireNonNull(dispatcher);
        this.paymentGateway = Objects.requireNonNull(paymentGateway);
        this.subscriptions = Objects.requireNonNull(subscriptions);
    }

    @Override
    public void listenTo(UserRegistered event) {
        PaymentMethodId paymentMethodId = PaymentMethodId.of(event.getPaymentMethodId());
        this.paymentGateway.makePayment(REGISTRATION_PRICE, paymentMethodId);

        ZonedDateTime now = this.clock.now();
        Subscription subscription = Subscription.create(
                SUBSCRIPTION_PRICE,
                paymentMethodId,
                event.getUserId(),
                now);
        this.subscriptions.save(subscription);

        this.dispatcher.dispatch(new SubscriptionCreated(
                subscription.getId().getValue().toString(),
                subscription.getSubscriberId()));
    }
}
