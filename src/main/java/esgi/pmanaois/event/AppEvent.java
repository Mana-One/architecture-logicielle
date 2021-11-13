package esgi.pmanaois.event;

import esgi.pmanaois.registration.domain.UniqueId;

import java.time.ZonedDateTime;

public interface AppEvent
{
    UniqueId getId();
    ZonedDateTime getCreatedAt();
}
