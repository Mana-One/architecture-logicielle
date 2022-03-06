package esgi.pmanaois.cc.modules.project.application.assignworker;

import esgi.pmanaois.cc.kernel.Command;

public class AssignWorker implements Command {
    public final String projectId;
    public final String worker;

    public AssignWorker(String projectId, String worker) {
        this.projectId = projectId;
        this.worker = worker;
    }
}
