package esgi.pmanaois.cc.modules.membership.domain;

import java.util.Optional;

final public class EmailValidationEngine {
    public Optional<String> validate(String email) {
        return (email == null || email.length() == 0) ?
                Optional.empty() :
                Optional.of(email);
    }
}
