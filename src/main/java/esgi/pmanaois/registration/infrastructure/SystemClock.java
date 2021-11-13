package esgi.pmanaois.registration.infrastructure;

import esgi.pmanaois.registration.domain.Clock;

import java.time.ZonedDateTime;

public class SystemClock implements Clock
{
    @Override
    public ZonedDateTime now() {
        return ZonedDateTime.now();
    }
}
