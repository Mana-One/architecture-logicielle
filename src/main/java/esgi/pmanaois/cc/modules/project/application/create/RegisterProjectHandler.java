package esgi.pmanaois.cc.modules.project.application.create;


import esgi.pmanaois.cc.kernel.CommandHandler;
import esgi.pmanaois.cc.kernel.Event;
import esgi.pmanaois.cc.kernel.EventDispatcher;
import esgi.pmanaois.cc.modules.project.domain.model.Owner;
import esgi.pmanaois.cc.modules.project.domain.model.Project;
import esgi.pmanaois.cc.modules.project.domain.model.ProjectId;
import esgi.pmanaois.cc.modules.project.domain.repository.ProjectRepository;

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
