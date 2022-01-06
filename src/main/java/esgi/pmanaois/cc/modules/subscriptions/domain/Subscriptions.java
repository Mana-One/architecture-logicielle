package esgi.pmanaois.cc.modules.subscriptions.domain;

import esgi.pmanaois.cc.kernel.Repository;

import java.time.ZonedDateTime;
import java.util.List;

public interface Subscriptions extends Repository<SubscriptionId, Subscription> {
    List<Subscription> listByDueDate(ZonedDateTime dueDate);
}
