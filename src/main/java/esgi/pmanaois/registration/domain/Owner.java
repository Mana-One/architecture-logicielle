package esgi.pmanaois.registration.domain;

import esgi.pmanaois.common.Role;

import java.util.Objects;

public final class Owner
{
    private final String firstName;
    private final String lastName;
    private final String email;
    private final Role role;

    private Owner(String firstName, String lastName, String email, Role role)
    {
        this.checkFirstName(Objects.requireNonNull(firstName));
        this.checkLastName(Objects.requireNonNull(lastName));
        this.checkEmail(Objects.requireNonNull(email));

        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.role = Objects.requireNonNull(role);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() { return email; }

    public Role getRole() {return this.role; }

    public static Owner createContractor(String firstName, String lastName, String email)
    {
        return new Owner(firstName, lastName, email, Role.CONTRACTOR);
    }

    public static Owner createTradesman(String firstName, String lastName, String email)
    {
        return new Owner(firstName, lastName, email, Role.TRADESMAN);
    }

    @Override
    public String toString()
    {
        return "{" +
                "'firstName': '" + this.firstName + "', " +
                "'lastName': '" + this.lastName + "', " +
                "'email': '" + this.email + "', " +
                "'role': '" + this.role.getName() + "'" +
                "}";
    }

    private void checkFirstName(String firstName)
    {
        if (firstName.length() == 0)
        {
            throw new IllegalArgumentException("Firstname is empty");
        }
    }

    private void checkLastName(String lastName)
    {
        if (lastName.length() == 0)
        {
            throw new IllegalArgumentException("Lastname is empty");
        }
    }

    private void checkEmail(String email)
    {
        if (!EmailValidationEngine.getInstance().test(email))
        {
            throw new IllegalArgumentException(("Email is invalid"));
        }
    }
}
