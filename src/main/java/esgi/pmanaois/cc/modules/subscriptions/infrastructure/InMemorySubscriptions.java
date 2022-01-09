package esgi.pmanaois.cc.modules.subscriptions.infrastructure;

import esgi.pmanaois.cc.modules.common.PaymentMethodId;
import esgi.pmanaois.cc.modules.subscriptions.domain.*;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

final public class InMemorySubscriptions implements Subscriptions {
    final private Map<SubscriptionId, List<SubscriptionEvent>> data = new ConcurrentHashMap<>();

    @Override
    public Optional<Subscription> findById(SubscriptionId id) {
        List<SubscriptionEvent> events = this.data.get(id);
        if (events == null) {
            return Optional.empty();
        }

        Subscription subscription = Subscription.init(id);
        subscription.replay(events);
        return Optional.of(subscription);
    }

    @Override
    public void remove(SubscriptionId id) {
        this.data.remove(id);
    }

    @Override
    public void save(Subscription subscription) {
        List<SubscriptionEvent> events = this.data.get(subscription.getId());
        if (events != null) {
            events.addAll(subscription.getEvents());
            return;
        }
        this.data.put(subscription.getId(), subscription.getEvents());
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
