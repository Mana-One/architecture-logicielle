package modules.membership.domain;

import java.util.Optional;

final public class EmailValidationEngine {
    public Optional<String> validate(String email) {
        return Optional.of(email);
    }
}
