package esgi.pmanaois.cc.modules.common;

public class PaymentMethodId {
    private final String value;

    private PaymentMethodId(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static PaymentMethodId of(String value) {
        return new PaymentMethodId(value);
    }
}
