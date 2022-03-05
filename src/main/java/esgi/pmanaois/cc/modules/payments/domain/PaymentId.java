package esgi.pmanaois.cc.modules.payments.domain;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import esgi.pmanaois.cc.kernel.UniqueId;

public class PaymentId implements UniqueId {
    final private UUID value;

    private PaymentId(UUID value) {
        this.value = Objects.requireNonNull(value);
    }

    public UUID getValue() {
        return value;
    }

    public static PaymentId generate() {
        return new PaymentId(UUID.randomUUID());
    }

    public Optional<PaymentId> fromString(String uid) {
        try {
            return Optional.of(new PaymentId(UUID.fromString(uid)));
        } catch(Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof PaymentId)) return false;
        PaymentId that = (PaymentId) obj;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    
}
