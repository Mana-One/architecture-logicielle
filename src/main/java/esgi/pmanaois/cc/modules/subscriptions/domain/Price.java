package esgi.pmanaois.cc.modules.subscriptions.domain;

import java.util.Objects;
import java.util.Optional;

public class Price {
    final private double value;
    final private String currency;

    private Price(double value, String currency) {
        this.value = Objects.requireNonNull(value);
        this.currency = Objects.requireNonNull(currency);
    }

    public double getValue() {
        return value;
    }

    public String getCurrency() {
        return currency;
    }

    private static Optional<Price> create(double value, String currency) {
        if (Double.compare(0.0, value) > 0) {
            return Optional.empty();
        }
        return Optional.of(new Price(value, currency));
    }

    public static Optional<Price> createWithEUR(double value) {
        return create(value, "EUR");
    }

    public static Price of(double value, String currency) {
        return new Price(value, currency);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Price)) return false;
        Price price = (Price) o;
        return Double.compare(price.value, value) == 0 && Objects.equals(currency, price.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, currency);
    }
}
