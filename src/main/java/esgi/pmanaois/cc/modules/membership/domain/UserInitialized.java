package esgi.pmanaois.cc.modules.membership.domain;

import java.util.Objects;

public class UserInitialized implements UserEvent {
    final private String firstName;
    final private String lastName;
    final private String email;
    final private Role role;
    final private String paymentMethodId;

    public UserInitialized(String firstName, String lastName, String email, Role role, String paymentMethodId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
        this.paymentMethodId = paymentMethodId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public Role getRole() {
        return role;
    }

    public String getPaymentMethodId() {
        return paymentMethodId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserInitialized)) return false;
        UserInitialized that = (UserInitialized) o;
        return Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(email, that.email) && role == that.role && Objects.equals(paymentMethodId, that.paymentMethodId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, email, role, paymentMethodId);
    }
}
