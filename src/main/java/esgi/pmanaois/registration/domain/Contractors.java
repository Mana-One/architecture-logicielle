package esgi.pmanaois.registration.domain;

public interface Contractors
{
    Contractor findById(UniqueId id);
    void save(Contractor contractor);
}
