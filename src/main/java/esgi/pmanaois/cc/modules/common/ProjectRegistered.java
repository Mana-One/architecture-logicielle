package esgi.pmanaois.cc.modules.common;

import esgi.pmanaois.cc.kernel.ApplicationEvent;

final public class UserRegistered implements ApplicationEvent {
    private final String userId;
    private final String role;
    private final String paymentMethodId;

    public UserRegistered(String userId, String role, String paymentMethodId) {
        this.userId = userId;
        this.role = role;
        this.paymentMethodId = paymentMethodId;
    }

    public String getUserId() {
        return userId;
    }

    public String getRole() {
        return role;
    }

    public String getPaymentMethodId() {
        return paymentMethodId;
    }
}
