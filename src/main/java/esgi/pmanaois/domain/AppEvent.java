package esgi.pmanaois.domain;

import java.time.ZonedDateTime;

public interface AppEvent
{
    UniqueId getId();
    ZonedDateTime getCreatedAt();
}
