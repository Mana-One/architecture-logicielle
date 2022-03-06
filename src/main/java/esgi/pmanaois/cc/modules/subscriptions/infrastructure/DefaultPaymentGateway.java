package esgi.pmanaois.cc.modules.subscriptions.infrastructure;

import esgi.pmanaois.cc.modules.common.PaymentMethodId;
import esgi.pmanaois.cc.modules.common.Price;
import esgi.pmanaois.cc.modules.subscriptions.application.PaymentGateway;

final public class DefaultPaymentGateway implements PaymentGateway {
    @Override
    public void makePayment(Price price, PaymentMethodId paymentMethodId) {
        System.out.println("Payment of " + price.getValue() + " " + price.getCurrency());
    }
}