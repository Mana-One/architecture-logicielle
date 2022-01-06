package esgi.pmanaois.cc.kernel;

import java.time.ZonedDateTime;

final public class SystemClock implements Clock {
    @Override
    public ZonedDateTime now() {
        return ZonedDateTime.now();
    }
}
