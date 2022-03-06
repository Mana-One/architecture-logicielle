package esgi.pmanaois.cc.modules.project.domain.model;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import esgi.pmanaois.cc.kernel.UniqueId;

public class Worker implements UniqueId {
    private final UUID value;
    
    private Worker(UUID value) {
        this.value = Objects.requireNonNull(value);
    }

    public static Optional<Worker> fromString(String uid) {
        try {
            return Optional.of(new Worker(UUID.fromString(uid)));
        } catch(Exception e) {
            return Optional.empty();
        }
    }

    public UUID getValue() {
        return this.value;
    }
}
