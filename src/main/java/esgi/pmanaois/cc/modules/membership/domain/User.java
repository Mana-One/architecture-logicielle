package esgi.pmanaois.cc.modules.membership.domain;

import esgi.pmanaois.cc.kernel.Entity;
import esgi.pmanaois.cc.modules.common.PaymentMethodId;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

final public class User implements Entity<UserId> {
    final private UserId id;
    private Name name;
    private String email;
    private Role role;
    private PaymentMethodId paymentMethodId;
    private boolean isVerified;
    private final List<UserEvent> events;

    private User(UserId id, List<UserEvent> events) {
        this.id = Objects.requireNonNull(id);
        this.events = Objects.requireNonNull(events);
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

    public List<UserEvent> getEvents() {
        return events;
    }

    public void verify() {
        this.events.add(new UserVerified());
    }

    public static User init(UserId id) {
        return new User(id, new ArrayList<UserEvent>());
    }

    public static User create(Name name, String email, Role role, PaymentMethodId paymentMethodId) {
            List<UserEvent> events = new ArrayList<>();
            events.add(new UserInitialized(
                    name.getFirst(),
                    name.getLast(),
                    email,
                    role,
                    paymentMethodId.getValue()
            ));

            User user = new User(UserId.generate(), events);
            user.name = name;
            user.email = email;
            user.role = role;
            user.paymentMethodId = paymentMethodId;
            return user;
    }

    private void apply(UserInitialized event) {
        this.name = Name.of(event.getFirstName(), event.getLastName());
        this.email = event.getEmail();
        this.role = event.getRole();
        this.paymentMethodId = PaymentMethodId.of(event.getPaymentMethodId());
    }

    private void apply(UserVerified event) {
        this.isVerified = true;
    }

    public void replay(List<UserEvent> events) {
        for (UserEvent event: events) {
            if (event instanceof UserInitialized) {
                this.apply((UserInitialized) event);
            }
            if (event instanceof UserVerified) {
                this.apply((UserVerified) event);
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }
}
