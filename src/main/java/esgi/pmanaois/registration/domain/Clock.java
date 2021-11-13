package esgi.pmanaois.registration.domain;

import java.time.ZonedDateTime;

public interface Clock
{
    ZonedDateTime now();
}
