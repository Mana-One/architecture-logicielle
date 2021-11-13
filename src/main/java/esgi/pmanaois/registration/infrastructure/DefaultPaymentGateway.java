package esgi.pmanaois.registration.infrastructure;

import esgi.pmanaois.registration.domain.CreditCard;
import esgi.pmanaois.registration.domain.PaymentAmount;
import esgi.pmanaois.registration.domain.PaymentGateway;

public class DefaultPaymentGateway implements PaymentGateway
{
    @Override
    public void processRegistration(CreditCard card, PaymentAmount registrationFee)
    {
        System.out.println("A registration fee of " + registrationFee + " was paid with card: " + card);
    }
}