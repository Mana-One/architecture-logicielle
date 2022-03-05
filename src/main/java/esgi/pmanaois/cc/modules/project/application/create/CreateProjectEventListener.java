package fr.al_cc2.application.project.create;

import fr.al_cc2.kernel.event.EventListener;

public class CreateProjectEventListener implements EventListener<CreateProjectEvent> {
    @Override
    public void listenTo(CreateProjectEvent event) { System.out.println("listening CreateProjectEvent."); }
}
