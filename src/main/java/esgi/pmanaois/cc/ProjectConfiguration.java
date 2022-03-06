package esgi.pmanaois.cc;


import esgi.pmanaois.cc.kernel.CommandBus;
import esgi.pmanaois.cc.kernel.EventDispatcher;
import esgi.pmanaois.cc.kernel.QueryBus;
import esgi.pmanaois.cc.modules.project.application.close.CloseProject;
import esgi.pmanaois.cc.modules.project.application.close.CloseProjectHandler;
import esgi.pmanaois.cc.modules.project.application.close.ProjectClosed;
import esgi.pmanaois.cc.modules.project.application.close.ProjectClosedEventListener;
import esgi.pmanaois.cc.modules.project.application.create.RegisterProject;
import esgi.pmanaois.cc.modules.project.application.create.RegisterProjectHandler;
import esgi.pmanaois.cc.modules.project.application.list.ListProjects;
import esgi.pmanaois.cc.modules.project.application.list.ListProjectsHandler;
import esgi.pmanaois.cc.modules.project.domain.OwnerValidationEngine;
import esgi.pmanaois.cc.modules.project.domain.ProjectService;
import esgi.pmanaois.cc.modules.project.domain.Projects;
import esgi.pmanaois.cc.modules.project.infrastructure.InMemoryProjectRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProjectConfiguration {
    @Autowired
    KernelConfiguration kernelConfiguration;
    @Autowired
    CommonConfiguration commonConfiguration;

    @Bean
    public Projects projectRepository() {
        return new InMemoryProjectRepository();
    }

    @Bean
    public OwnerValidationEngine ownerValidationEngine() {
        return new OwnerValidationEngine();
    }

    @Bean
    public ProjectService projectService() {
        return new ProjectService(ownerValidationEngine(), projectRepository(), kernelConfiguration.clock());
    }

    @Bean
    public RegisterProjectHandler registerProjectHandler() {
        return new RegisterProjectHandler(projectRepository(), projectService(), kernelConfiguration.eventDispatcher());
    }

    @Bean
    public CloseProjectHandler closeProjectHandler() {
        return new CloseProjectHandler(projectRepository(), projectService(), kernelConfiguration.eventDispatcher());
    }

    @Bean
    public ListProjectsHandler listProjectsHandler() {
        return new ListProjectsHandler(projectRepository());
    }

    @Bean
    public CommandBus projectCommandBus() {
        final CommandBus commandBus = kernelConfiguration.commandBus();
        commandBus.addHandler(RegisterProject.class, registerProjectHandler());
        commandBus.addHandler(CloseProject.class, closeProjectHandler());
        return commandBus;
    }

    @Bean
    public QueryBus projectQueryBus() {
        final QueryBus queryBus = kernelConfiguration.queryBus();
        queryBus.addHandler(ListProjects.class, listProjectsHandler());
        return queryBus;
    }

    @Bean
    public ProjectClosedEventListener projectClosedEventListener() {
        EventDispatcher dispatcher = kernelConfiguration.eventDispatcher();
        ProjectClosedEventListener listener = new ProjectClosedEventListener();
        dispatcher.addListener(ProjectClosed.class, listener);
        return listener;
    }
}
