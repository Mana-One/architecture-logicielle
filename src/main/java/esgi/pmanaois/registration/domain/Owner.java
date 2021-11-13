package esgi.pmanaois.registration.domain;

import java.util.Objects;

final class Owner
{
    private final String firstName;
    private final String lastName;
    private final String email;

    private Owner(String firstName, String lastName, String email)
    {
        this.firstName = Objects.requireNonNull(firstName);
        this.lastName = Objects.requireNonNull(lastName);
        this.email = Objects.requireNonNull(email);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() { return email; }

    public static Owner of(String firstName, String lastName, String email)
    {
        if (firstName.length() == 0)
        {
            throw new IllegalArgumentException("Firstname is empty");
        }

        if (lastName.length() == 0)
        {
            throw new IllegalArgumentException(("Lastname is empty"));
        }

        if (!EmailValidationEngine.getInstance().test(email))
        {
            throw new IllegalArgumentException(("Email is invalid"));
        }

        return new Owner(firstName, lastName, email);
    }

    @Override
    public String toString()
    {
        return "{" +
                "'firstName': '" + this.firstName + "', " +
                "'lastName': '" + this.lastName + "', " +
                "'email': '" + this.email + "'" +
                "}";
    }
}
