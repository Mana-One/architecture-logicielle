package esgi.pmanaois.domain;

import java.time.ZonedDateTime;
import java.util.Objects;

public class RegistrationFeePaidEvent implements AppEvent
{
    private final UniqueId id;
    private final ZonedDateTime createdAt;
    private final Contractor contractor;

    private RegistrationFeePaidEvent(UniqueId id, ZonedDateTime createdAt, Contractor contractor)
    {
        this.id = id;
        this.createdAt = createdAt;
        this.contractor = Objects.requireNonNull(contractor);
    }

    public static RegistrationFeePaidEvent withContractor(Contractor contractor)
    {
        return new RegistrationFeePaidEvent(
                UniqueId.generateWithUUID(),
                ZonedDateTime.now(),
                contractor
        );
    }

    @Override
    public UniqueId getId()
    {
        return this.id;
    }

    @Override
    public ZonedDateTime getCreatedAt()
    {
        return this.createdAt;
    }

    public Contractor getContractor()
    {
        return this.contractor;
    }

    @Override
    public String toString()
    {
        return "{" + "'id': " + this.id +
                ", 'createdAt': '" + this.createdAt +
                "', 'contractor': " +this.contractor +
                "}";
    }
}
