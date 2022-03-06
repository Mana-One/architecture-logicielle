package esgi.pmanaois.cc.modules.membership.application;

import java.util.Objects;

import esgi.pmanaois.cc.kernel.EventListener;
import esgi.pmanaois.cc.modules.common.WorkerAssigned;
import esgi.pmanaois.cc.modules.membership.domain.UserService;

public class WorkerAssignedListener implements EventListener<WorkerAssigned> {
    final private UserService service;
    
    public WorkerAssignedListener(UserService service) {
        this.service = Objects.requireNonNull(service);
    }

    @Override
    public void listenTo(WorkerAssigned event) {
        try {
            this.service.markAsAssigned(event.tradesmanId);
            System.out.println("Worker with id " + event.tradesmanId + " assigned.");
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
}
