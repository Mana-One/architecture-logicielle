package esgi.pmanaois.cc.modules.project.application;

import esgi.pmanaois.cc.kernel.Clock;
import esgi.pmanaois.cc.modules.project.domain.*;
import esgi.pmanaois.cc.modules.project.domain.model.Owner;
import esgi.pmanaois.cc.modules.project.domain.model.Project;
import esgi.pmanaois.cc.modules.project.domain.model.ProjectId;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

final public class ProjectService {
    final private OwnerValidationEngine ownerValidationEngine;
    final private Projects projects;
    final private Clock clock;

    public ProjectService(
            OwnerValidationEngine ownerValidationEngine,
            Projects projects, Clock clock) {
        this.ownerValidationEngine = Objects.requireNonNull(ownerValidationEngine);
        this.projects = projects;
        this.clock = clock;
    }

    public Project create(String name, String owner, List<String> requiredSkills) throws InvalidProjectState {

        final List<String> errors = new ArrayList<>();

        final Optional<String> optName = Optional.ofNullable(name);
        if (optName.isEmpty()) {
            errors.add("Invalid name.");
        }

        final Optional<Owner> optOwner = Owner.fromString(owner);
        if (optOwner.isEmpty() || ownerValidationEngine.validate(optOwner.get()).isEmpty()) {
            errors.add("Invalid owner.");
        }

        if (!errors.isEmpty()) {
            throw InvalidProjectState.fromMessages(errors);
        }

        return Project.create(name, optOwner.get(), requiredSkills, clock.now());
    }

    public Project retrieveProject(ProjectId projectId) {
        Optional<Project> optionalProject = projects.findById(projectId);
        if (optionalProject.isEmpty()) throw NoSuchProject.withId(projectId);
        return optionalProject.get();
    }

    public void closeProject(Project project) {
        project.setEndDate(clock.now());
        project.setStatus(Status.DONE);
    }
}
