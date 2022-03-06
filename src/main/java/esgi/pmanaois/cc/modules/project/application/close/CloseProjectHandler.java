package esgi.pmanaois.cc.modules.project.application.close;

import esgi.pmanaois.cc.kernel.CommandHandler;
import esgi.pmanaois.cc.modules.project.domain.ProjectService;

final public class CloseProjectHandler implements CommandHandler<CloseProject, Void> {
    final private ProjectService projectService;

    public CloseProjectHandler(ProjectService projectService) {
        this.projectService = projectService;
    }

    @Override
    public Void handle(CloseProject command) {
        projectService.closeProject(command.projectId);
        return null;
    }
}
