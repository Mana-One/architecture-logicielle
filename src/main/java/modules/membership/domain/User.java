package modules.membership.domain;

import java.util.Objects;

final public class User {
    final private UserId id;
    private String firstName;
    private String lastName;
    private String email;
    private Role role;
    private PaymentMethodId paymentMethodId;
    private boolean isVerified;

    private User(UserId id, String firstName, String lastName, String email, Role role, PaymentMethodId paymentMethodId, boolean isVerified) {
        this.id = Objects.requireNonNull(id);
        this.firstName = Objects.requireNonNull(firstName);
        this.lastName = Objects.requireNonNull(lastName);
        this.email = Objects.requireNonNull(email);
        this.role = Objects.requireNonNull(role);
        this.paymentMethodId = Objects.requireNonNull(paymentMethodId);
        this.isVerified = Objects.requireNonNull(isVerified);
    }

    public static User create(String firstName, String lastName, String email, Role role, PaymentMethodId paymentMethodId) {
        return new User(UserId.generate(), firstName, lastName, email, role, paymentMethodId, false);
    }
}
