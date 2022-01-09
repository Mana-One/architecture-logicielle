package esgi.pmanaois.cc.modules.common;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PaymentMethodId)) return false;
        PaymentMethodId that = (PaymentMethodId) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
