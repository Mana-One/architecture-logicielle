package esgi.pmanaois.cc.modules.project.application.close;

import esgi.pmanaois.cc.kernel.CommandHandler;
import esgi.pmanaois.cc.kernel.EventDispatcher;
import esgi.pmanaois.cc.modules.project.domain.ProjectService;
import esgi.pmanaois.cc.modules.project.domain.Projects;
import esgi.pmanaois.cc.modules.project.domain.model.Project;

final public class CloseProjectHandler implements CommandHandler<CloseProject, Void> {
    final private Projects projects;
    final private ProjectService projectService;
    final private EventDispatcher eventDispatcher;

    public CloseProjectHandler(Projects projects, ProjectService projectService, EventDispatcher eventDispatcher) {
        this.projects = projects;
        this.projectService = projectService;
        this.eventDispatcher = eventDispatcher;
    }

//    @Override
//    public Void handle(RegisterUser command) {
//        final User user = this.userService.create(
//                command.firstName,
//                command.lastName,
//                command.email,
//                command.role,
//                command.paymentMethodId);
//
//        this.users.save(user);
//        this.eventDispatcher.dispatch(new UserRegistered(
//                user.getId().getValue().toString(),
//                command.role,
//                command.paymentMethodId));
//        return null;
//    }

    @Override
    public Void handle(CloseProject command) {
        Project project = projectService.retrieveProject(command.projectId);
        projectService.closeProject(project);
        projects.save(project);
        eventDispatcher.dispatch(new ProjectClosed());
        return null;
    }
}
