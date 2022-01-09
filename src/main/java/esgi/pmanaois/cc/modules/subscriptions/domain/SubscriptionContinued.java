package esgi.pmanaois.cc.modules.subscriptions.domain;

import java.time.ZonedDateTime;
import java.util.Objects;

final public class SubscriptionContinued implements SubscriptionEvent {
    final private ZonedDateTime started;
    final private ZonedDateTime dueDate;

    public SubscriptionContinued(ZonedDateTime started, ZonedDateTime dueDate) {
        this.started = started;
        this.dueDate = dueDate;
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
        if (!(o instanceof SubscriptionContinued)) return false;
        SubscriptionContinued that = (SubscriptionContinued) o;
        return Objects.equals(started, that.started) && Objects.equals(dueDate, that.dueDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(started, dueDate);
    }
}
