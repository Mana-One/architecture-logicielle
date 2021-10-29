package esgi.pmanaois.infrastructure;

import esgi.pmanaois.domain.Contractor;
import esgi.pmanaois.domain.Contractors;
import esgi.pmanaois.domain.UniqueId;

import javax.naming.OperationNotSupportedException;

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
