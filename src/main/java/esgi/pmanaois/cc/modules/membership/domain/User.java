package esgi.pmanaois.cc.modules.membership.domain;

import esgi.pmanaois.cc.kernel.Entity;
import esgi.pmanaois.cc.modules.common.PaymentMethodId;
import esgi.pmanaois.cc.modules.membership.InvalidUserState;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

final public class User implements Entity<UserId> {
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

    public UserId getId() {
        return id;
    }

    public Name getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Role getRole() {
        return role;
    }

    public PaymentMethodId getPaymentMethodId() {
        return paymentMethodId;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public void verify() {
        this.isVerified = true;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return isVerified == user.isVerified && Objects.equals(id, user.id) && Objects.equals(name, user.name) && Objects.equals(email, user.email) && role == user.role && Objects.equals(paymentMethodId, user.paymentMethodId);
    }
}
