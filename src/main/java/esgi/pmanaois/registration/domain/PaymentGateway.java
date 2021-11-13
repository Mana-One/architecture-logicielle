package esgi.pmanaois.registration.domain;

public interface PaymentGateway
{
    void processRegistration(Contractor contractor, PaymentAmount registrationFee);
}
