package esgi.pmanaois.cc.modules.project.domain;

import esgi.pmanaois.cc.kernel.Clock;
import esgi.pmanaois.cc.modules.project.domain.model.Owner;
import esgi.pmanaois.cc.modules.project.domain.model.Project;
import esgi.pmanaois.cc.modules.project.domain.model.ProjectId;
import esgi.pmanaois.cc.modules.project.domain.model.Worker;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

final public class ProjectService {
    final private OwnerValidationEngine ownerValidationEngine;
    final private WorkerMatcher workerMatcher;
    final private Projects projects;
    final private Clock clock;

    public ProjectService(
        OwnerValidationEngine ownerValidationEngine,
        WorkerMatcher workerMatcher,
        Projects projects, 
        Clock clock
    ) {
        this.ownerValidationEngine = Objects.requireNonNull(ownerValidationEngine);
        this.workerMatcher = workerMatcher;
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

    public void assignWorker(String projectId, String worker) {
        final List<String> errors = new ArrayList<>();

        final Optional<ProjectId> optId = ProjectId.fromString(projectId);
        if (optId.isEmpty()) {
            errors.add("Invalid project id.");
        }

        final Optional<Worker> optWorker = Worker.fromString(worker);
        if (optWorker.isEmpty()) {
            errors.add("Invalid or unsuitable worker.");
        }

        if (!errors.isEmpty()) {
            throw InvalidProjectState.fromMessages(errors);
        }

        Project project = this.retrieveProject(optId.get());
        if (!this.workerMatcher.isSuitableForProject(optWorker.get(), project)) {
            throw new InvalidProjectState("Invalid or unsuitable worker.");
        }

        project.assignWorker(optWorker.get());
        projects.save(project);
    }

    public Project retrieveProject(ProjectId projectId) {
        Optional<Project> optionalProject = projects.findById(projectId);
        if (optionalProject.isEmpty()) throw NoSuchProject.withId(projectId);
        return optionalProject.get();
    }

    public void closeProject(String projectId) {
        final Optional<ProjectId> optId = ProjectId.fromString(projectId);
        if (optId.isEmpty()) {
            throw new InvalidProjectState("Invalid project id.");
        }

        Project project = this.retrieveProject(optId.get());
        project.setEndDate(clock.now());
        project.setStatus(Status.DONE);

        projects.save(project);
    }
}
