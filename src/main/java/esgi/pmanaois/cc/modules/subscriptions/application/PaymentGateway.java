package esgi.pmanaois.cc.modules.subscriptions.application;

import esgi.pmanaois.cc.modules.common.PaymentMethodId;
import esgi.pmanaois.cc.modules.common.Price;

public interface PaymentGateway {
    void makePayment(Price price, PaymentMethodId paymentMethodId);
}
