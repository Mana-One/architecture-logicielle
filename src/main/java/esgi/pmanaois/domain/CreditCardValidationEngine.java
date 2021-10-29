package esgi.pmanaois.domain;

import java.util.function.Predicate;

final class CreditCardValidationEngine implements Predicate<CreditCard>
{
    private static final CreditCardValidationEngine INSTANCE = new CreditCardValidationEngine();

    private CreditCardValidationEngine() {}
    @Override
    public boolean test(CreditCard creditCard) {
        return true;
    }

    public static CreditCardValidationEngine getInstance()
    {
        return CreditCardValidationEngine.INSTANCE;
    }
}
