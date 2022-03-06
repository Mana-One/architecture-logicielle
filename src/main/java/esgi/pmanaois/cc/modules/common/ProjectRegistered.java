package esgi.pmanaois.cc.modules.common;

import esgi.pmanaois.cc.kernel.ApplicationEvent;
import esgi.pmanaois.cc.modules.project.domain.model.Owner;

final public class ProjectRegistered implements ApplicationEvent {
    private final String ProjectId;
    private final Owner Owner;
    private final String status;

    public ProjectRegistered(String projectId, Owner owner, String status) {
        ProjectId = projectId;
        Owner = owner;
        this.status = status;
    }

    public String getProjectId() {
        return ProjectId;
    }

    public Owner getOwner() {
        return Owner;
    }

    public String getStatus() {
        return status;
    }
}
