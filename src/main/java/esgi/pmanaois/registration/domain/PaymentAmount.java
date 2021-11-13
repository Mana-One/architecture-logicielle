package esgi.pmanaois.registration.domain;

import java.util.Objects;

public final class PaymentAmount {
    private String currencyCode;
    private double amount;

    private PaymentAmount(String currencyCode, double amount)
    {
        this.currencyCode = Objects.requireNonNull(currencyCode);
        this.amount = this.checkAmountPrecondition(amount);
    }

    private double checkAmountPrecondition(double amount)
    {
        Objects.requireNonNull(amount);
        if (Double.compare(amount, 0.00) < 0)
        {
            throw new IllegalArgumentException("Amount cannot be negative");
        }
        return amount;
    }

    public static PaymentAmount withEuros(double amount)
    {
        return new PaymentAmount("EUR", amount);
    }

     @Override
    public String toString()
     {
         return String.format("%.2f %s", this.amount, this.currencyCode);
     }
}
