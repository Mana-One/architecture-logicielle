package esgi.pmanaois.common;

import esgi.pmanaois.event.AppEvent;

import java.time.ZonedDateTime;
import java.util.Objects;

public final class RegistrationFeePaidEvent implements AppEvent
{
    private final UniqueId id;
    private final ZonedDateTime createdAt;
    private final CardOwnerDto payload;

    private RegistrationFeePaidEvent(UniqueId id, ZonedDateTime createdAt, CardOwnerDto payload)
    {
        this.id = id;
        this.createdAt = createdAt;
        this.payload = Objects.requireNonNull(payload);
    }

    public static RegistrationFeePaidEvent of(ZonedDateTime createdAt, CardOwnerDto payload)
    {
        return new RegistrationFeePaidEvent(
                UniqueId.generateWithUUID(),
                createdAt,
                payload
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

    public CardOwnerDto getPayload()
    {
        return this.payload;
    }

    @Override
    public String toString()
    {
        return "{" + "'id': '" + this.id.getValue() + "', " +
                "'createdAt': '" + this.createdAt + "', " +
                "'payload': " + this.payload +
                "}";
    }
}
