package esgi.pmanaois.domain;

public final class RegisterContractorService
{
    private final Contractors contractors;

    public RegisterContractorService(Contractors contractors)
    {
        this.contractors = contractors;
    }

    public void register(Contractor contractor)
    {
        this.contractors.save(contractor);
    }
}
