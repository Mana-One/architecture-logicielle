package esgi.pmanaois.infrastructure;

import esgi.pmanaois.domain.Clock;

import java.time.ZonedDateTime;

public class SystemClock implements Clock
{
    @Override
    public ZonedDateTime now() {
        return ZonedDateTime.now();
    }
}
