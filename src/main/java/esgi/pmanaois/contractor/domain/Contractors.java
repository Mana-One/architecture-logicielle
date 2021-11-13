package esgi.pmanaois.contractor.domain;

import esgi.pmanaois.common.UniqueId;

public interface Contractors
{
    Contractor findById(UniqueId id);
    void save(Contractor contractor);
}
