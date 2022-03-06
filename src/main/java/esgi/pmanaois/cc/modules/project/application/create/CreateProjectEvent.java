package esgi.pmanaois.cc.modules.project.application.create;


import esgi.pmanaois.cc.kernel.ApplicationEvent;
import esgi.pmanaois.cc.modules.project.domain.model.ProjectId;

public class CreateProjectEvent implements ApplicationEvent {
    private final ProjectId projectId;

    public CreateProjectEvent(ProjectId projectId) {
        this.projectId = projectId;
    }
}