package modules.membership.domain;

import esgi.kernel.UniqueId;

import java.util.Objects;
import java.util.UUID;

final public class UserId implements UniqueId {
    private UUID value;

    private UserId(UUID value) {
        this.value = Objects.requireNonNull(value);
    }

    @Override
    public String toString() {
        return "UserId{\n" +
                "\tvalue: '" + this.value.toString() + "'\n" +
                "}";
    }

    public static UserId generate() {
        return new UserId(UUID.randomUUID());
    }
}
