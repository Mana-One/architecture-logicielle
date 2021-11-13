package esgi.pmanaois.event;

import esgi.pmanaois.domain.UniqueId;

import java.time.ZonedDateTime;

public interface AppEvent
{
    UniqueId getId();
    ZonedDateTime getCreatedAt();
}
