package esgi.pmanaois.cc.modules.payments.domain;

public enum PaymentStatus {
    PENDING("Pending"),
    FULFILLED("Fulfilled");

    private final String name;

    private PaymentStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
