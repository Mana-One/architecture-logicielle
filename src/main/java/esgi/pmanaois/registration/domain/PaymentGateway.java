package esgi.pmanaois.registration.domain;

public interface PaymentGateway
{
    void processRegistration(CreditCard card, PaymentAmount registrationFee);
}
