package esgi.pmanaois.cc.modules.project.domain.model;

import esgi.pmanaois.cc.kernel.UniqueId;
import esgi.pmanaois.cc.modules.membership.domain.UserId;

import java.util.Objects;
import java.util.UUID;

public final class ProjectId implements UniqueId {
    private final UUID value;

    private ProjectId(UUID value) {
        this.value = Objects.requireNonNull(value);
    }

    public UUID getValue() {
        return value;
    }

    public static ProjectId generate() {
        return new ProjectId(UUID.randomUUID());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectId projectId = (ProjectId) o;
        return value == projectId.value;
    }

    @Override
    public int hashCode() { return Objects.hash(value); }
}
