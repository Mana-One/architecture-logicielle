package esgi.pmanaois.cc.kernel;

import java.time.ZonedDateTime;

public interface Clock {
    ZonedDateTime now();
}
