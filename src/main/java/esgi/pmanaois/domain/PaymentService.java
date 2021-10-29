package esgi.pmanaois.domain;

public class PaymentService {
    private static final PaymentAmount REGISTRATION_FEE = PaymentAmount.withEuros(20.0);

    private final PaymentGateway paymentGateway;

    public PaymentService(PaymentGateway paymentGateway)
    {
        this.paymentGateway = paymentGateway;
    }

    public void payRegistrationFee(Contractor contractor)
    {
        this.paymentGateway.processRegistration(contractor, REGISTRATION_FEE);
    }
}
