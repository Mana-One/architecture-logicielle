package esgi.pmanaois.cc.modules.project.domain.model;

import esgi.pmanaois.cc.kernel.UniqueId;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

public final class ProjectId implements UniqueId {
    private final UUID value;

    private ProjectId(UUID value) {
        this.value = Objects.requireNonNull(value);
    }

    public static Optional<ProjectId> fromString(String projectId) {
        try {
            return Optional.of(new ProjectId(UUID.fromString(projectId)));
        } catch(Exception e) {
            return Optional.empty();
        }
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

        return Objects.equals(value, projectId.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
