package esgi.pmanaois.cc.modules.common;

import esgi.pmanaois.cc.kernel.Event;

public class WorkerAssigned implements Event {
    final public String tradesmanId;

    public WorkerAssigned(String tradesmanId) {
        this.tradesmanId = tradesmanId;
    }
}
