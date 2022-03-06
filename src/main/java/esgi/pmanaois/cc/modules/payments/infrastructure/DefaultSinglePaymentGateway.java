package esgi.pmanaois.cc.modules.payments.infrastructure;

import esgi.pmanaois.cc.modules.common.Price;
import esgi.pmanaois.cc.modules.payments.application.SinglePaymentGateway;

public class DefaultSinglePaymentGateway implements SinglePaymentGateway {
    @Override
    public void makePaymentOnBehalfOf(String emitterId, String recipientId, Price amount) {
        System.out.println(
            "Payment of " + 
            amount.getValue() + " " + amount.getCurrency() + 
            " from account " + emitterId +
            " to account " + recipientId
        );
    }
}
