package esgi.pmanaois.common;

import java.util.Objects;

public final class CardOwnerDto
{
    public String firstName;
    public String lastName;
    public String email;
    public String role;

    public CardOwnerDto(String firstName, String lastName, String email, String role)
    {
        this.firstName = Objects.requireNonNull(firstName);
        this.lastName = Objects.requireNonNull(lastName);
        this.email = Objects.requireNonNull(email);
        this.role = Objects.requireNonNull(role);
    }

    @Override
    public String toString()
    {
        return "{" +
                "'firstName': '" + this.firstName + "', " +
                "'lastName': '" + this.lastName + "', " +
                "'email': '" + this.email + "', " +
                "'role': '" + this.role + "'" +
                "}";
    }
}
