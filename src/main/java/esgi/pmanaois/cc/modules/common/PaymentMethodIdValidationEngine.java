package esgi.pmanaois.cc.modules.common;

import java.util.Optional;

public interface PaymentMethodIdValidationEngine {
    Optional<PaymentMethodId> validate(String paymentMethodId);
}
