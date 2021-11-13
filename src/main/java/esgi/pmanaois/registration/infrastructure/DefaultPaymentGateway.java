package esgi.pmanaois.registration.infrastructure;

import esgi.pmanaois.registration.domain.Contractor;
import esgi.pmanaois.registration.domain.PaymentAmount;
import esgi.pmanaois.registration.domain.PaymentGateway;

public class DefaultPaymentGateway implements PaymentGateway
{
    @Override
    public void processRegistration(Contractor contractor, PaymentAmount registrationFee) {
        System.out.println("Contractor " + contractor + " has paid a registration fee of " + registrationFee);
    }
}