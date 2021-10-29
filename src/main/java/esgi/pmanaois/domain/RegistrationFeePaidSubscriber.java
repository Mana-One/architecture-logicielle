package esgi.pmanaois.domain;

public class RegistrationFeePaidSubscriber implements Subscriber<RegistrationFeePaidEvent>
{
    private final RegisterContractorService registerContractorService;

    public RegistrationFeePaidSubscriber(RegisterContractorService registerContractorService)
    {
        this.registerContractorService = registerContractorService;
    }

    @Override
    public void on(RegistrationFeePaidEvent event) {
        System.out.println("Event received: " + event);
        this.registerContractorService.register(event.getContractor());
    }
}
