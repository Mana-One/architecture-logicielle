package esgi.pmanaois.registration.infrastructure;

import esgi.pmanaois.registration.domain.Contractor;
import esgi.pmanaois.registration.domain.Contractors;
import esgi.pmanaois.registration.domain.UniqueId;

public class DefaultContractors implements Contractors
{
    @Override
    public Contractor findById(UniqueId id) {
        throw new AssertionError();
    }

    @Override
    public void save(Contractor contractor) {
        System.out.println("Contractor " + contractor + " was registered");
    }
}
