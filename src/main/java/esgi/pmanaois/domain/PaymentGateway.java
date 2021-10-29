package esgi.pmanaois.domain;

public interface PaymentGateway
{
    void processRegistration(Contractor contractor, PaymentAmount registrationFee);
}
