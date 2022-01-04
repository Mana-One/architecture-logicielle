package modules.membership.domain;

public class PaymentMethodId {
    private final String value;

    private PaymentMethodId(String value) {
        this.value = value;
    }

    public static PaymentMethodId of(String value) {
        return new PaymentMethodId(value);
    }
}
