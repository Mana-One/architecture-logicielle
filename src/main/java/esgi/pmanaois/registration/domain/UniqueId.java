package esgi.pmanaois.registration.domain;

import java.util.UUID;

public final class UniqueId
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
