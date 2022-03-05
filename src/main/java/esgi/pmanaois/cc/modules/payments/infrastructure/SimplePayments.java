package esgi.pmanaois.cc.modules.payments.infrastructure;

import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import esgi.pmanaois.cc.modules.common.Price;
import esgi.pmanaois.cc.modules.payments.domain.Payment;
import esgi.pmanaois.cc.modules.payments.domain.PaymentId;
import esgi.pmanaois.cc.modules.payments.domain.PaymentStatus;
import esgi.pmanaois.cc.modules.payments.domain.Payments;

public class SimplePayments implements Payments {
    final private Map<PaymentId, Payment> data = new HashMap<>();

    @Override
    public Optional<Payment> findById(PaymentId id) {
        Payment instance = this.data.get(id);
        return Optional.ofNullable(instance);
    }

    @Override
    public void remove(PaymentId id) {
        this.data.remove(id);
    }

    @Override
    public void save(Payment entity) {
        this.data.put(entity.getId(), entity);
    }

    @Override
    public List<Payment> listByDueDate(ZonedDateTime dueDate) {
        return List.of(
            Payment.of(PaymentId.generate(), Price.of(10.0, "EUR"), "payer", "recipient", dueDate, PaymentStatus.PENDING),
            Payment.of(PaymentId.generate(), Price.of(20.0, "EUR"), "payer", "recipient", dueDate, PaymentStatus.PENDING)
        );
    }
    
}
