package esgi.pmanaois.common;

public final class CardOwnerDto
{
    public String firstName;
    public String lastName;
    public String email;

    public CardOwnerDto(String firstName, String lastName, String email)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
}
