package esgi.pmanaois.common;

import java.time.ZonedDateTime;

public final class SystemClock implements Clock
{
    @Override
    public ZonedDateTime now() {
        return ZonedDateTime.now();
    }
}
