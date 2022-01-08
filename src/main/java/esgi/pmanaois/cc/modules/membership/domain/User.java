package esgi.pmanaois.cc.modules.membership.domain;

import esgi.pmanaois.cc.kernel.Entity;
import esgi.pmanaois.cc.modules.common.PaymentMethodId;

import java.util.Objects;

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

    public static User create(Name name, String email, Role role, PaymentMethodId paymentMethodId) {
            return new User(UserId.generate(), name, email, role, paymentMethodId, false);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return isVerified == user.isVerified && Objects.equals(id, user.id) && Objects.equals(name, user.name) && Objects.equals(email, user.email) && role == user.role && Objects.equals(paymentMethodId, user.paymentMethodId);
    }
}
