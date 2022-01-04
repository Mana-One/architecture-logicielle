package esgi.pmanaois.cc.modules.membership.domain;

import esgi.pmanaois.cc.modules.membership.InvalidUserState;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

final public class User {
    final private UserId id;
    private Name name;
    private String email;
    private Role role;
    private PaymentMethodId paymentMethodId;
    private boolean isVerified;

    private User(UserId id, Name name, String email, Role role, PaymentMethodId paymentMethodId, boolean isVerified) {
        this.id = Objects.requireNonNull(id);
        this.name = Objects.requireNonNull(name);
        this.email = Objects.requireNonNull(email);
        this.role = Objects.requireNonNull(role);
        this.paymentMethodId = Objects.requireNonNull(paymentMethodId);
        this.isVerified = Objects.requireNonNull(isVerified);
    }

    public static User create(
            String firstName,
            String lastName,
            String email,
            String role,
            String paymentMethodId,
            EmailValidationEngine emailValidationEngine
    ) {
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

        if (paymentMethodId == null || paymentMethodId.length() == 0) {
            errors.add("Invalid payment method id.");
        }

        if (!errors.isEmpty()) {
            throw InvalidUserState.fromMessages(errors);
        }

        return new User(
                UserId.generate(),
                optName.get(),
                optEmail.get(),
                optRole.get(),
                PaymentMethodId.of(paymentMethodId),
                false);
    }
}
