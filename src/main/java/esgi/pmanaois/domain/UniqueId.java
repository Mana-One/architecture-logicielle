package esgi.pmanaois.domain;

import java.util.UUID;

public class UniqueId
{
    private final String value;

    private UniqueId(String value)
    {
        this.value = value;
    }

    @Override
    public String toString()
    {
        return "{" +
            "'value': '" + this.value + "'" +
            "}";
    }

    public static UniqueId generateWithUUID()
    {
        return new UniqueId(UUID.randomUUID().toString());
    }
}