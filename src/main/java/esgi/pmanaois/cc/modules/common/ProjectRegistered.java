package esgi.pmanaois.cc.modules.common;

import esgi.pmanaois.cc.kernel.ApplicationEvent;

final public class ProjectRegistered implements ApplicationEvent {
    private final String projectId;
    private final String owner;

    public ProjectRegistered(String projectId, String owner) {
        this.projectId = projectId;
        this.owner = owner;
    }

    public String getProjectId() {
        return projectId;
    }

    public String getOwner() {
        return owner;
    }
}
