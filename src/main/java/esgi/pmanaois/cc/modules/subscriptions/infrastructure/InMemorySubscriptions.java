package esgi.pmanaois.cc.modules.subscriptions.infrastructure;

import esgi.pmanaois.cc.modules.common.PaymentMethodId;
import esgi.pmanaois.cc.modules.subscriptions.domain.Price;
import esgi.pmanaois.cc.modules.subscriptions.domain.Subscription;
import esgi.pmanaois.cc.modules.subscriptions.domain.SubscriptionId;
import esgi.pmanaois.cc.modules.subscriptions.domain.Subscriptions;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

final public class InMemorySubscriptions implements Subscriptions {
    final private Map<SubscriptionId, Subscription> data = new ConcurrentHashMap<>();

    @Override
    public Optional<Subscription> findById(SubscriptionId id) {
        return Optional.ofNullable(this.data.get(id));
    }

    @Override
    public void remove(SubscriptionId id) {
        this.data.remove(id);
    }

    @Override
    public void save(Subscription subscription) {
        this.data.put(subscription.getId(), subscription);
    }

    @Override
    public List<Subscription> listByDueDate(ZonedDateTime dueDate) {
        Subscription sub1 = Subscription.of(
                SubscriptionId.generate(),
                Price.of(30.0, "EUR"),
                PaymentMethodId.of("some pm"),
                "some id",
                dueDate,
                dueDate
        );
        Subscription sub2 = Subscription.of(
                SubscriptionId.generate(),
                Price.of(30.0, "EUR"),
                PaymentMethodId.of("some pm"),
                "some id",
                dueDate,
                dueDate
        );
        return List.of(sub1, sub2);
    }
}
