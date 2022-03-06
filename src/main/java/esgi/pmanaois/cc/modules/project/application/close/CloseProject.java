package esgi.pmanaois.cc.modules.project.application.close;

import esgi.pmanaois.cc.kernel.Command;

final public class CloseProject implements Command {
    final public String projectId;

    public CloseProject(String projectId) {
        this.projectId = projectId;
    }
}