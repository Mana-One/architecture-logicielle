package esgi.pmanaois;

import esgi.pmanaois.common.RegistrationFeePaidEvent;
import esgi.pmanaois.user.infrastructure.RegistrationFeePaidSubscriber;
import esgi.pmanaois.user.domain.RegisterUserService;
import esgi.pmanaois.registration.domain.*;
import esgi.pmanaois.user.infrastructure.DefaultUsers;
import esgi.pmanaois.event.DefaultEventBus;
import esgi.pmanaois.registration.infrastructure.DefaultPaymentGateway;
import esgi.pmanaois.common.SystemClock;

import java.util.Collections;

public class App 
{
    public static void main( String[] args )
    {
        var owner = Owner.createContractor("Paolo", "Manaois", "username@domain.com");
        var card = CreditCard.of(
                "0000000000000000",
                "000",
                owner);

        var contractorsRepository = new DefaultUsers();
        var registerContractorService = new RegisterUserService(contractorsRepository);

        var subscriptionMap = Collections.singletonMap(
                RegistrationFeePaidEvent.class,
                Collections.singletonList(new RegistrationFeePaidSubscriber(registerContractorService))
        );
        var eventBus = new DefaultEventBus(subscriptionMap);

        var clock = new SystemClock();
        var paymentGateway = new DefaultPaymentGateway();
        var paymentService = new PaymentService(paymentGateway, eventBus, clock);

        paymentService.payRegistrationFee(card);
    }
}
