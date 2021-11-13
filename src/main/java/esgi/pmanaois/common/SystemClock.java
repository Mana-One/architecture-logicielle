package esgi.pmanaois.common;

import java.time.ZonedDateTime;

public class SystemClock implements Clock
{
    @Override
    public ZonedDateTime now() {
        return ZonedDateTime.now();
    }
}
