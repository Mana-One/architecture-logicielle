package esgi.pmanaois.common;

import java.util.UUID;

public final class UniqueId
{
    private final String value;

    private UniqueId(String value)
    {
        this.value = value;
    }

    public String getValue() {return this.value; }

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
