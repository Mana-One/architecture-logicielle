package esgi.pmanaois.cc.modules.project.application.assignworker;

import esgi.pmanaois.cc.kernel.CommandHandler;
import esgi.pmanaois.cc.kernel.Event;
import esgi.pmanaois.cc.kernel.EventDispatcher;
import esgi.pmanaois.cc.modules.common.WorkerAssigned;
import esgi.pmanaois.cc.modules.project.domain.ProjectService;

public class AssignWorkerHandler implements CommandHandler<AssignWorker, Void> {
    final private ProjectService projectService;
    final private EventDispatcher<Event> eventDispatcher;

    public AssignWorkerHandler(ProjectService projectService, EventDispatcher<Event> eventDispatcher) {
        this.projectService = projectService;
        this.eventDispatcher = eventDispatcher;
    }

    @Override
    public Void handle(AssignWorker command) {
        this.projectService.assignWorker(command.projectId, command.worker);
        this.eventDispatcher.dispatch(new WorkerAssigned(command.worker));
        return null;
    }
}