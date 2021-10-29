package esgi.pmanaois.infrastructure;

import esgi.pmanaois.domain.Contractor;
import esgi.pmanaois.domain.PaymentAmount;
import esgi.pmanaois.domain.PaymentGateway;

public class DefaultPaymentGateway implements PaymentGateway
{
    @Override
    public void processRegistration(Contractor contractor, PaymentAmount registrationFee) {
        System.out.println("Contractor " + contractor + " has paid a registration fee of " + registrationFee);
    }
}