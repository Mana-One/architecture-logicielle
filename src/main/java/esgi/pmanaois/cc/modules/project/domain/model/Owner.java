package esgi.pmanaois.cc.modules.project.domain.model;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import esgi.pmanaois.cc.kernel.UniqueId;

public class Owner implements UniqueId {
    private final UUID value;

    private Owner(UUID value) {
        this.value = Objects.requireNonNull(value);
    }

    public static Optional<Owner> fromString(String value) {
        try {
            return Optional.of(new Owner(UUID.fromString(value)));
        } catch(Exception e) {
            return Optional.empty();
        }
    }

    public static Owner of(UUID uid) {
        return new Owner(uid);
    }

    public UUID getValue() {
        return this.value;
    }
}
