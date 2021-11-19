package esgi.pmanaois.registration.domain;

import java.util.Objects;

public final class CreditCard {
    private final String number;
    private final String securityCode;
    private final Owner owner;

    private CreditCard(String number, String securityCode, Owner owner)
    {
        this.number = Objects.requireNonNull(number);
        this.securityCode = Objects.requireNonNull(securityCode);
        this.owner = Objects.requireNonNull(owner);
    }

    public Owner getOwner() { return this.owner; }

    public static CreditCard of(
            String number,
            String securityCode,
            Owner owner)
    {
        var card = new CreditCard(
                number,
                securityCode,
                owner);

        if (CreditCardValidationEngine.getInstance().test(card))
        {
            return card;
        }
        throw new IllegalArgumentException("Provided card is invalid");
    }

    @Override
    public String toString()
    {
        return "{" +
                "'number': " + this.number + ", " +
                "'securityCode': '" + this.securityCode + "', " +
                "'owner': " + this.owner +
                "}";
    }
}
