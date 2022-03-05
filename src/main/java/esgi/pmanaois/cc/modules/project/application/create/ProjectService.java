package esgi.pmanaois.cc.modules.project.domain.model;

import esgi.pmanaois.cc.modules.common.PaymentMethodId;
import esgi.pmanaois.cc.modules.common.PaymentMethodIdValidationEngine;
import esgi.pmanaois.cc.modules.membership.domain.*;

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

    public User create(
            String firstName,
            String lastName,
            String email,
            String role,
            String paymentMethodId
    ) throws InvalidUserState {

        final List<String> errors = new ArrayList<String>();
        final Optional<Name> optName = Name.create(firstName, lastName);
        if (optName.isEmpty()) {
            errors.add("Invalid firstname and/or lastname.");
        }

        final Optional<String> optEmail = emailValidationEngine.validate(email);
        if (optEmail.isEmpty()) {
            errors.add("Invalid email.");
        }

        final Optional<Role> optRole = Role.fromString(role);
        if (optRole.isEmpty()) {
            errors.add("Invalid role.");
        }

        final Optional<PaymentMethodId> optPaymentMethodId = this.paymentMethodIdValidationEngine
                .validate(paymentMethodId);
        if (optPaymentMethodId.isEmpty()) {
            errors.add("Invalid payment method id.");
        }

        if (!errors.isEmpty()) {
            throw InvalidUserState.fromMessages(errors);
        }

        return User.create(optName.get(), email, optRole.get(), optPaymentMethodId.get());
    }
}
