package esgi.pmanaois.cc.modules.payments.application;

import esgi.pmanaois.cc.modules.common.Price;

public interface SinglePaymentGateway {
    void makePaymentOnBehalfOf(String emitterId, String recipientId, Price amount);
}
