package esgi.pmanaois;

import esgi.pmanaois.domain.Contractor;
import esgi.pmanaois.domain.RegisterContractorService;
import esgi.pmanaois.infrastructure.DefaultContractors;
import esgi.pmanaois.infrastructure.DefaultPaymentGateway;
import esgi.pmanaois.domain.PaymentService;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        var contractor = Contractor.of("Paolo", "Manaois", "username@domain.com");
        var paymentGateway = new DefaultPaymentGateway();
        var paymentService = new PaymentService(paymentGateway);
        var contractorsRepository = new DefaultContractors();
        var registerContractorService = new RegisterContractorService(contractorsRepository);

        paymentService.payRegistrationFee(contractor);
        registerContractorService.register(contractor);
    }
}
