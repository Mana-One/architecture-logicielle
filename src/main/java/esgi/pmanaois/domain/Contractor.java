package esgi.pmanaois.domain;

import java.util.Objects;

public class Contractor
{
    private final UniqueId id;
    private String firstName;
    private String lastName;
    private String email;

    private Contractor(UniqueId id, String firstName, String lastName, String email)
    {
        this.id = id;
        this.firstName = Objects.requireNonNull(firstName);
        this.lastName = Objects.requireNonNull(lastName);
        this.email = Objects.requireNonNull(email);
    }

    @Override
    public String toString()
    {
        return "{" +
                "'id': " + this.id + ", " +
                "'firstName': '" + this.firstName + "', " +
                "'lastName': '" + this.lastName + "'" +
                "'email': '" + this.email + "'" +
                "}";
    }

    public static Contractor of(String firstName, String lastName, String email)
    {
        if (EmailValidationEngine.getInstance().test(email)) {
            return new Contractor(
                    UniqueId.generateWithUUID(),
                    firstName,
                    lastName,
                    email
            );
        }
        throw new IllegalArgumentException("Invalid email");
    }
}
