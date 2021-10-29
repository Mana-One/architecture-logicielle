package esgi.pmanaois.domain;

public interface Contractors
{
    Contractor findById(UniqueId id);
    void save(Contractor contractor);
}
