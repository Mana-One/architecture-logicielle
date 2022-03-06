package esgi.pmanaois.cc.modules.project.domain;

import esgi.pmanaois.cc.modules.project.domain.model.Owner;

import java.util.Optional;

final public class OwnerValidationEngine {
    public Optional<Owner> validate(Owner owner) {
        return (owner == null) ?
                Optional.empty() :
                Optional.of(owner);
    }
}
