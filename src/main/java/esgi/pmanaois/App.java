package esgi.pmanaois;

import esgi.pmanaois.domain.Contractor;
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
        paymentService.payRegistrationFee(contractor);
    }
}
