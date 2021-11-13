package esgi.pmanaois.registration.domain;

import esgi.pmanaois.common.CardOwnerDto;
import esgi.pmanaois.common.Clock;
import esgi.pmanaois.common.RegistrationFeePaidEvent;
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

    public void payRegistrationFee(CreditCard card)
    {
        this.paymentGateway.processRegistration(card, REGISTRATION_FEE);
        Owner owner = card.getOwner();
        this.eventBus.dispatch(RegistrationFeePaidEvent.of(
                this.clock.now(),
                new CardOwnerDto(
                        owner.getFirstName(),
                        owner.getLastName(),
                        owner.getEmail())));
    }
}
