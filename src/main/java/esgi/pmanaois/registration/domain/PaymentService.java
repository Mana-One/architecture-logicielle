package esgi.pmanaois.registration.domain;

import esgi.pmanaois.event.AppEvent;
import esgi.pmanaois.event.EventBus;

public final class PaymentService {
    private static final PaymentAmount REGISTRATION_FEE = PaymentAmount.withEuros(20.0);
    private final PaymentGateway paymentGateway;
    private final EventBus<AppEvent> eventBus;
    private final Clock clock;

    public PaymentService(PaymentGateway paymentGateway, EventBus<AppEvent> eventBus, Clock clock)
    {
        this.paymentGateway = paymentGateway;
        this.eventBus = eventBus;
        this.clock = clock;
    }

    public void payRegistrationFee(Contractor contractor)
    {
        this.paymentGateway.processRegistration(contractor, REGISTRATION_FEE);
        this.eventBus.dispatch(RegistrationFeePaidEvent.of(this.clock.now(), contractor));
    }
}
