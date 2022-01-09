package esgi.pmanaois.cc.modules.common;

import esgi.pmanaois.cc.kernel.Event;

import java.time.ZonedDateTime;

final public class PaymentsInitiated implements Event {
    final private ZonedDateTime dueDate;

    public PaymentsInitiated(ZonedDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public ZonedDateTime getDueDate() {
        return dueDate;
    }
}
