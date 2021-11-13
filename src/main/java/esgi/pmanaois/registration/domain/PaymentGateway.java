package esgi.pmanaois.registration.domain;

import esgi.pmanaois.common.Contractor;

public interface PaymentGateway
{
    void processRegistration(Contractor contractor, PaymentAmount registrationFee);
}
