package fr.al_cc2.application.project.create;

import fr.al_cc2.domain.model.ProjectId;
import fr.al_cc2.kernel.ApplicationEvent;

public class CreateProjectEvent implements ApplicationEvent {
    private final ProjectId projectId;

    public CreateProjectEvent(ProjectId projectId) {
        this.projectId = projectId;
    }
}