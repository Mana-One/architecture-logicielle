package esgi.pmanaois.cc.modules.project.application.close;

import esgi.pmanaois.cc.kernel.Command;
import esgi.pmanaois.cc.modules.project.domain.model.ProjectId;

final public class CloseProject implements Command {
    final public ProjectId projectId;

    public CloseProject(ProjectId projectId) {
        this.projectId = projectId;
    }
}