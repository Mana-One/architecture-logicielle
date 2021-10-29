package esgi.pmanaois.domain;

import java.util.function.Predicate;

public class EmailValidationEngine implements Predicate<String>
{
    private static final EmailValidationEngine INSTANCE = new EmailValidationEngine();

    private EmailValidationEngine() {}

    @Override
    public boolean test(String s)
    {
        return true;
    }

    public static EmailValidationEngine getInstance()
    {
        return EmailValidationEngine.INSTANCE;
    }
}