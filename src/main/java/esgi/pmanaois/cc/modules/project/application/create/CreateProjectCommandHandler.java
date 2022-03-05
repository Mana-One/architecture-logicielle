package fr.al_cc2.application.project.create;

import fr.al_cc2.domain.model.*;
import fr.al_cc2.domain.repository.ProjectRepository;
import fr.al_cc2.kernel.command.CommandHandler;
import fr.al_cc2.kernel.event.Event;
import fr.al_cc2.kernel.event.EventDispatcher;

public class CreateProjectCommandHandler implements CommandHandler<CreateProject, ProjectId> {

    private final ProjectRepository projectRepository;
    private final EventDispatcher<Event> eventEventDispatcher;

    public CreateProjectCommandHandler(ProjectRepository projectRepository, EventDispatcher<Event> eventEventDispatcher) {
        this.projectRepository = projectRepository;
        this.eventEventDispatcher = eventEventDispatcher;

    }

    @Override
    public ProjectId handle(CreateProject createProject) {
        final ProjectId projectId = projectRepository.nextIdentity();
        Project project = Project.of(projectId, createProject.name, new Owner(createProject.owner.getName()), createProject.status);
        projectRepository.add(project);
        eventEventDispatcher.dispatch(new CreateProjectEvent(projectId));
        return projectId;
    }
}
