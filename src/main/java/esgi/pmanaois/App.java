package esgi.pmanaois;

import esgi.pmanaois.common.Contractor;
import esgi.pmanaois.common.RegistrationFeePaidEvent;
import esgi.pmanaois.common.RegistrationFeePaidSubscriber;
import esgi.pmanaois.contractor.domain.RegisterContractorService;
import esgi.pmanaois.registration.domain.*;
import esgi.pmanaois.contractor.infrastructure.DefaultContractors;
import esgi.pmanaois.event.DefaultEventBus;
import esgi.pmanaois.registration.infrastructure.DefaultPaymentGateway;
import esgi.pmanaois.common.SystemClock;

import java.util.Collections;

public class App 
{
    public static void main( String[] args )
    {
        var contractor = Contractor.of(
                "Paolo",
                "Manaois",
                "username@domain.com"
        );

        var contractorsRepository = new DefaultContractors();
        var registerContractorService = new RegisterContractorService(contractorsRepository);

        var subscriptionMap = Collections.singletonMap(
                RegistrationFeePaidEvent.class,
                Collections.singletonList(new RegistrationFeePaidSubscriber(registerContractorService))
        );
        var eventBus = new DefaultEventBus(subscriptionMap);

        var clock = new SystemClock();
        var paymentGateway = new DefaultPaymentGateway();
        var paymentService = new PaymentService(paymentGateway, eventBus, clock);

        paymentService.payRegistrationFee(contractor);
    }
}
