package fr.al_cc2.domain.model;

import fr.al_cc2.kernel.Entity;

import java.time.LocalDate;
import java.util.Objects;

public final class Project implements Entity<ProjectId> {
    private final ProjectId id;
    private final String name;
    private final Owner owner;
    private final String status;
    private final LocalDate startDate;
    private LocalDate endDate;

    public Project(ProjectId id, String name, Owner owner, String status) {
        this.id = Objects.requireNonNull(id);
        this.name = Objects.requireNonNull(name);
        this.owner = Objects.requireNonNull(owner);
        this.status = Objects.requireNonNull(status);
        this.startDate = Objects.requireNonNull(LocalDate.now());
        this.endDate = null;
    }

    public static Project of(ProjectId id, String name, Owner owner, String status) {
        return new Project(id, name, owner, status);
    }

    public ProjectId getId() { return id; }

    @Override
    public ProjectId id() { return null; }

    public String getName() {
        return name;
    }

    public Owner getOwner() {
        return owner;
    }

    public String getStatus() {
        return status;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }
}
