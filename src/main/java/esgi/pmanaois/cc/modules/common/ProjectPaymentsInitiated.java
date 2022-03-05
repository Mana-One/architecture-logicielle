package esgi.pmanaois.cc.modules.common;
import java.time.ZonedDateTime;

import esgi.pmanaois.cc.kernel.Event;

public class ProjectPaymentsInitiated implements Event {
    final private ZonedDateTime dueDate;

    public ProjectPaymentsInitiated(ZonedDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public ZonedDateTime getDueDate() {
        return dueDate;
    }
}
