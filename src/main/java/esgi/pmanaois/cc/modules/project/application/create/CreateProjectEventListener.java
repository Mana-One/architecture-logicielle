package esgi.pmanaois.cc.modules.project.application.create;


import esgi.pmanaois.cc.kernel.EventListener;

public class CreateProjectEventListener implements EventListener<CreateProjectEvent> {
    @Override
    public void listenTo(CreateProjectEvent event) { System.out.println("listening CreateProjectEvent."); }
}
