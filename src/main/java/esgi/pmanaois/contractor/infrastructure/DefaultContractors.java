package esgi.pmanaois.contractor.infrastructure;

import esgi.pmanaois.contractor.domain.Contractor;
import esgi.pmanaois.contractor.domain.Contractors;
import esgi.pmanaois.common.UniqueId;

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
