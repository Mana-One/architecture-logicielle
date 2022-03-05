package esgi.pmanaois.cc.modules.payments.domain;

import java.time.ZonedDateTime;
import java.util.List;

import esgi.pmanaois.cc.kernel.Repository;

public interface Payments extends Repository<PaymentId, Payment> {
    List<Payment> listByDueDate(ZonedDateTime dueDate);
}
