package esgi.pmanaois.cc.modules.project.application.create;

import esgi.pmanaois.cc.modules.membership.domain.Role;
import esgi.pmanaois.cc.modules.project.domain.InvalidProjectState;
import esgi.pmanaois.cc.modules.project.domain.OwnerValidationEngine;
import esgi.pmanaois.cc.modules.project.domain.Status;
import esgi.pmanaois.cc.modules.project.domain.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

final public class ProjectService {
    final private OwnerValidationEngine ownerValidationEngine;

    public ProjectService(
            OwnerValidationEngine ownerValidationEngine
    ) {
        this.ownerValidationEngine = Objects.requireNonNull(ownerValidationEngine);
    }

    public Project create(String name, Owner owner, String status) throws InvalidProjectState {

        final List<String> errors = new ArrayList<>();

        final Optional<String> optName = Optional.ofNullable(name);
        if (optName.isEmpty()) {
            errors.add("Invalid name.");
        }

        final Optional<Owner> optOwner = ownerValidationEngine.validate(owner);
        if (optOwner.isEmpty()) {
            errors.add("Invalid owner.");
        }

        final Optional<Status> optStatus = Status.fromString(status);
        if (optStatus.isEmpty()) {
            errors.add("Invalid status.");
        }

        if (!errors.isEmpty()) {
            throw InvalidProjectState.fromMessages(errors);
        }

        return Project.create(name, owner, optStatus.get());
    }
}
