package esgi.pmanaois.domain;

import java.time.ZonedDateTime;

public interface Clock
{
    ZonedDateTime now();
}
