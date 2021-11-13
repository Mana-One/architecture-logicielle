package esgi.pmanaois.contractor.infrastructure;

import esgi.pmanaois.common.RegistrationFeePaidEvent;
import esgi.pmanaois.contractor.domain.Contractor;
import esgi.pmanaois.contractor.domain.RegisterContractorService;
import esgi.pmanaois.event.Subscriber;

public final class RegistrationFeePaidSubscriber implements Subscriber<RegistrationFeePaidEvent>
{
    private final RegisterContractorService registerContractorService;

    public RegistrationFeePaidSubscriber(RegisterContractorService registerContractorService)
    {
        this.registerContractorService = registerContractorService;
    }

    @Override
    public void on(RegistrationFeePaidEvent event) {
        System.out.println(event.getClass().getSimpleName() + " received: " + event);
        Contractor contractor = Contractor.fromCardOwnerDto(event.getPayload());
        this.registerContractorService.register(contractor);
    }
}
