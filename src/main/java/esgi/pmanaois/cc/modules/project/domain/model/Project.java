package esgi.pmanaois.cc.modules.project.domain.model;

import esgi.pmanaois.cc.kernel.Entity;
import esgi.pmanaois.cc.modules.project.domain.Status;

import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

final public class Project implements Entity<ProjectId> {
    private final ProjectId id;
    private final String name;
    private final Owner owner;
    private Status status;
    private final List<String> requiredSkills;
    private final ZonedDateTime startDate;
    private ZonedDateTime endDate;

    private Project(ProjectId id, String name, Owner owner, Status status, List<String> requiredSkills, ZonedDateTime startDate) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.status = status;
        this.requiredSkills = requiredSkills;
        this.startDate = startDate;
    }

    public static Project create(String name, Owner owner, List<String> requiredSkills, ZonedDateTime startDate) {
        return new Project(ProjectId.generate(), name, owner, Status.WAITING, requiredSkills, startDate);
    }

    public ProjectId getId() {
        return id;
    }

    public ProjectId id() {
        return null;
    }

    public String getName() {
        return name;
    }

    public Owner getOwner() {
        return owner;
    }

    public Status getStatus() {
        return status;
    }

    public List<String> getRequiredSkills() {
        return Collections.unmodifiableList(this.requiredSkills);
    }

    public ZonedDateTime getStartDate() {
        return startDate;
    }

    public ZonedDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(ZonedDateTime endDate) {
        this.endDate = endDate;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Project)) return false;
        Project project = (Project) o;
        return Objects.equals(id, project.id);
    }
}
