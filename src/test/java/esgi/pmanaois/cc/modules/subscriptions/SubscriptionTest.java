package esgi.pmanaois.cc.modules.subscriptions;

import esgi.pmanaois.cc.modules.common.PaymentMethodId;
import esgi.pmanaois.cc.modules.common.Price;
import esgi.pmanaois.cc.modules.subscriptions.domain.Subscription;
import esgi.pmanaois.cc.modules.subscriptions.domain.SubscriptionContinued;
import esgi.pmanaois.cc.modules.subscriptions.domain.SubscriptionInitialized;
import org.junit.Assert;
import org.junit.Test;

import java.time.ZonedDateTime;

public class SubscriptionTest {
    @Test
    public void creation_should_generate_SubscriptionInitializedEvent() {
        Price price = Price.of(30.0, "EUR");
        PaymentMethodId paymentMethodId = PaymentMethodId.of("some id");
        String subscriberId = "subscriberId";
        ZonedDateTime started = ZonedDateTime.now();

        Subscription subscription = Subscription.create(price, paymentMethodId, subscriberId, started);

        Assert.assertEquals(1, subscription.getEvents().size());
        Assert.assertTrue(subscription.getEvents().get(0) instanceof SubscriptionInitialized);
    }

    @Test
    public void setForNextMonth_should_generate_SubscriptionContinuedEvent() {
        Price price = Price.of(30.0, "EUR");
        PaymentMethodId paymentMethodId = PaymentMethodId.of("some id");
        String subscriberId = "subscriberId";
        ZonedDateTime started = ZonedDateTime.now().minusMonths(1);
        ZonedDateTime dueDate = started.plusMonths(1);
        Subscription subscription = Subscription.create(price, paymentMethodId, subscriberId, started);

        subscription.setForNextMonth(dueDate);

        Assert.assertEquals(2, subscription.getEvents().size());
        Assert.assertTrue(subscription.getEvents().get(1) instanceof SubscriptionContinued);
    }

    @Test
    public void replay_should_map_the_correct_attributes() {
        Price price = Price.of(30.0, "EUR");
        PaymentMethodId paymentMethodId = PaymentMethodId.of("some id");
        String subscriberId = "subscriberId";
        ZonedDateTime started = ZonedDateTime.now().minusMonths(1);
        ZonedDateTime dueDate = started.plusMonths(1);
        Subscription subscription = Subscription.create(price, paymentMethodId, subscriberId, started);
        subscription.setForNextMonth(dueDate);

        subscription.replay(subscription.getEvents());

        Assert.assertEquals(price, subscription.getPrice());
        Assert.assertEquals(paymentMethodId, subscription.getPaymentMethodId());
        Assert.assertEquals(subscriberId, subscription.getSubscriberId());
        Assert.assertEquals(dueDate, subscription.getStarted());
        Assert.assertEquals(dueDate.plusMonths(1), subscription.getDueDate());
    }
}
