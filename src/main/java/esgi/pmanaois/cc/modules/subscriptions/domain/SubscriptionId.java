package esgi.pmanaois.cc.modules.subscriptions.domain;

import esgi.pmanaois.cc.kernel.UniqueId;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

public class SubscriptionId implements UniqueId {
    final private UUID value;

    private SubscriptionId(UUID value) {
        this.value = Objects.requireNonNull(value);
    }

    public UUID getValue() {
        return value;
    }

    public static SubscriptionId generate() {
        return new SubscriptionId(UUID.randomUUID());
    }

    public static Optional<SubscriptionId> fromString(String uid) {
        try {
            return Optional.of(new SubscriptionId(UUID.fromString(uid)));
        } catch(Exception ex) {
            return Optional.empty();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SubscriptionId)) return false;
        SubscriptionId that = (SubscriptionId) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
