package esgi.pmanaois.cc;


import esgi.pmanaois.cc.kernel.*;
import esgi.pmanaois.cc.modules.membership.application.RegisterUser;
import esgi.pmanaois.cc.modules.membership.application.UserService;
import esgi.pmanaois.cc.modules.project.application.create.*;
import esgi.pmanaois.cc.modules.project.domain.OwnerValidationEngine;
import esgi.pmanaois.cc.modules.project.domain.Projects;
import esgi.pmanaois.cc.modules.project.domain.model.Project;
import esgi.pmanaois.cc.modules.project.domain.repository.ProjectRepository;
import esgi.pmanaois.cc.modules.project.infrastructure.InMemoryProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class ProjectConfiguration {
    @Autowired KernelConfiguration kernelConfiguration;
    @Autowired CommonConfiguration commonConfiguration;

    @Bean
    public Projects projectRepository() {
        return new InMemoryProjectRepository();
    }

    @Bean
    public OwnerValidationEngine ownerValidationEngine(){
        return new OwnerValidationEngine();
    }

    @Bean
    public ProjectService projectService(){
        return new ProjectService(ownerValidationEngine());
    }

    @Bean
    public RegisterProjectHandler registerProjectHandler() {
        return new RegisterProjectHandler(projectRepository(), projectService(), kernelConfiguration.eventDispatcher());
    }

    @Bean
    public CommandBus projectCommandBus() {
        final CommandBus commandBus = kernelConfiguration.commandBus();
        commandBus.addHandler(RegisterProject.class, registerProjectHandler());
        return commandBus;
    }
}
