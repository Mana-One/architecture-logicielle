package fr.al_cc2;

import fr.al_cc2.application.project.create.CreateProject;
import fr.al_cc2.application.project.create.CreateProjectCommandHandler;
import fr.al_cc2.application.project.create.CreateProjectEvent;
import fr.al_cc2.application.project.create.CreateProjectEventListener;
import fr.al_cc2.domain.repository.ProjectRepository;
import fr.al_cc2.infrastructure.DefaultEventDispatcher;
import fr.al_cc2.infrastructure.InMemoryProjectRepository;
import fr.al_cc2.kernel.command.Command;
import fr.al_cc2.kernel.command.CommandBus;
import fr.al_cc2.kernel.command.CommandHandler;
import fr.al_cc2.kernel.command.SimpleCommandBus;
import fr.al_cc2.kernel.event.Event;
import fr.al_cc2.kernel.event.EventDispatcher;
import fr.al_cc2.kernel.event.EventListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class ProjectConfiguration {
    @Bean
    public ProjectRepository projectRepository() {
        return new InMemoryProjectRepository();
    }

    @Bean
    public EventDispatcher<Event> projectEventDispatcher() {
        final Map<Class<? extends Event>, List<EventListener<? extends Event>>> listenerMap = new HashMap<>();
        listenerMap.put(CreateProjectEvent.class, List.of(new CreateProjectEventListener()));
        return new DefaultEventDispatcher(listenerMap);
    }

    @Bean
    public CreateProjectCommandHandler createProjectCommandHandler() {
        return new CreateProjectCommandHandler(projectRepository(), projectEventDispatcher());
    }

    @Bean
    public CommandBus projectCommandBus() {
        final Map<Class<? extends Command>, CommandHandler> commandHandlerMap = new HashMap<>();
        commandHandlerMap.put(CreateProject.class, new CreateProjectCommandHandler(projectRepository(), projectEventDispatcher()));
        return new SimpleCommandBus(commandHandlerMap);
    }
}
