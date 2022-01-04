package esgi.pmanaois.cc.modules.membership.domain;

import esgi.pmanaois.cc.kernel.UniqueId;

import java.util.Objects;
import java.util.UUID;

final public class UserId implements UniqueId {
    final private UUID value;

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
