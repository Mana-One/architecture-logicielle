package esgi.pmanaois.cc.modules.common;

import java.util.Optional;

public class DefaultPaymentMethodIdValidationEngine implements PaymentMethodIdValidationEngine {
    @Override
    public Optional<PaymentMethodId> validate(String paymentMethodId) {
        return Optional.of(PaymentMethodId.of(paymentMethodId));
    }
}
