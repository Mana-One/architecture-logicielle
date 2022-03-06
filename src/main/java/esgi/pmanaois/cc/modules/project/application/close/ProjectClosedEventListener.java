package esgi.pmanaois.cc.modules.project.application.close;

import esgi.pmanaois.cc.kernel.EventListener;

public final class ProjectClosedEventListener implements EventListener<ProjectClosed> {
    @Override
    public void listenTo(ProjectClosed event) {
        System.out.println("listening projectClosed event");
    }
}
