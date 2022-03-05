package fr.al_cc2.domain.model;

import fr.al_cc2.kernel.UniqueId;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

public class UserId implements UniqueId {
        final private UUID value;

        private UserId(UUID value) {
            this.value = Objects.requireNonNull(value);
        }

        public UUID getValue() {
            return value;
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

        public static Optional<UserId> fromString(String uid) {
            try {
                return Optional.of(new UserId(UUID.fromString(uid)));
            } catch (Exception ex) {
                return Optional.empty();
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof UserId)) return false;
            UserId userId = (UserId) o;
            return Objects.equals(value, userId.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(value);
        }
}

