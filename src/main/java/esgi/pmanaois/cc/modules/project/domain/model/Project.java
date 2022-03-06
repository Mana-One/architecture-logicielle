package esgi.pmanaois.cc.modules.project.domain.model;

import esgi.pmanaois.cc.kernel.Entity;
import esgi.pmanaois.cc.modules.membership.domain.*;
import esgi.pmanaois.cc.modules.membership.domain.UserId;
import esgi.pmanaois.cc.modules.project.domain.Status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

final public class Project implements Entity<ProjectId> {
    private final ProjectId id;
    private final String name;
    private final Owner owner;
    private final Status status;
    private final LocalDate startDate;
    private LocalDate endDate;

    private Project(ProjectId id, String name, Owner owner, Status status) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.status = status;
        this.startDate = LocalDate.now();
    }

    public static Project create(String name, Owner owner, Status status) {
        return new Project(ProjectId.generate(), name, owner, status);
    }

    public ProjectId getId() { return id; }

    public ProjectId id() { return null; }

    public String getName() {
        return name;
    }

    public Owner getOwner() {
        return owner;
    }

    public Status getStatus() {
        return status;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Project)) return false;
        Project project = (Project) o;
        return Objects.equals(id, project.id);
    }
}
