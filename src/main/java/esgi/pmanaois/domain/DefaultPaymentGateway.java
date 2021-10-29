package esgi.pmanaois.domain;

public class DefaultPaymentGateway implements PaymentGateway
{
    @Override
    public void processRegistration(Contractor contractor, PaymentAmount registrationFee) {
        System.out.println(contractor + " has paid a registration of " + registrationFee);
    }
}