package esgi.pmanaois.cc.modules.project.application.create;


import esgi.pmanaois.cc.kernel.CommandHandler;
import esgi.pmanaois.cc.kernel.Event;
import esgi.pmanaois.cc.kernel.EventDispatcher;
import esgi.pmanaois.cc.modules.common.ProjectRegistered;
import esgi.pmanaois.cc.modules.project.application.ProjectService;
import esgi.pmanaois.cc.modules.project.domain.Projects;
import esgi.pmanaois.cc.modules.project.domain.model.*;

import java.util.Objects;

public class RegisterProjectHandler implements CommandHandler<RegisterProject, ProjectId> {

    final private Projects projects;
    final private ProjectService projectService;
    final private EventDispatcher eventDispatcher;

    public RegisterProjectHandler(
            Projects projects,
            ProjectService projectService,
            EventDispatcher<Event> eventDispatcher
    ) {
        this.projects = Objects.requireNonNull(projects);
        this.projectService = Objects.requireNonNull(projectService);
        this.eventDispatcher = Objects.requireNonNull(eventDispatcher);
    }


    @Override
    public ProjectId handle(RegisterProject command) {
        final Project project = this.projectService.create(
                command.name,
                command.owner,
                command.status);

        this.projects.save(project);
        this.eventDispatcher.dispatch(new ProjectRegistered(
                project.getId().getValue().toString(),
                command.owner,
                command.status));

        return null;
    }
}
