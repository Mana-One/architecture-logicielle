package esgi.pmanaois.cc.modules.project.application.create;


import esgi.pmanaois.cc.kernel.CommandHandler;
import esgi.pmanaois.cc.modules.project.domain.ProjectService;
import esgi.pmanaois.cc.modules.project.domain.Projects;
import esgi.pmanaois.cc.modules.project.domain.model.*;

import java.util.Objects;

public class RegisterProjectHandler implements CommandHandler<RegisterProject, Void> {

    final private Projects projects;
    final private ProjectService projectService;

    public RegisterProjectHandler(
            Projects projects,
            ProjectService projectService
    ) {
        this.projects = Objects.requireNonNull(projects);
        this.projectService = Objects.requireNonNull(projectService);
    }


    @Override
    public Void handle(RegisterProject command) {
        final Project project = this.projectService.create(
                command.name,
                command.owner,
                command.requiredSkills);

        this.projects.save(project);
        return null;
    }
}
