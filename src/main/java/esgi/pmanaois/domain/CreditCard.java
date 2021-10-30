package esgi.pmanaois.domain;

import java.util.Objects;

final class CreditCard {
    private final String number;
    private final String securityCode;

    private CreditCard(String number, String securityCode)
    {
        this.number = Objects.requireNonNull(number);
        this.securityCode = Objects.requireNonNull(securityCode);
    }

    public static CreditCard of(String number, String securityCode)
    {
        var card = new CreditCard(number, securityCode);
        if (CreditCardValidationEngine.getInstance().test(card))
        {
            return card;
        }
        throw new IllegalArgumentException("Provided card is invalid");
    }
}
