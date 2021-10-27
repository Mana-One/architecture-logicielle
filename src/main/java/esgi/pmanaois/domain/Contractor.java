package esgi.pmanaois.domain;

import java.util.Objects;

public class Contractor
{
    private final UniqueId id;
    private String firstName;
    private String lastName;

    private Contractor(UniqueId id, String firstName, String lastName)
    {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString()
    {
        return "{" +
            "'id': " + this.id + ", " +
            "'firstName': '" + this.firstName + "', " +
            "'lastName': '" + this.lastName + "'" +
            "}";
    }

    public static Contractor of(String firstName, String lastName)
    {
        return new Contractor(
                UniqueId.generateWithUUID(),
                Objects.requireNonNull(firstName),
                Objects.requireNonNull(lastName)
        );
    }
}
