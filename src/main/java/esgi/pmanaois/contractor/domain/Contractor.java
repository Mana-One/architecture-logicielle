package esgi.pmanaois.contractor.domain;

import esgi.pmanaois.common.CardOwnerDto;
import esgi.pmanaois.common.UniqueId;

import java.util.Objects;

public final class Contractor
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
                "'id': " + this.id.getValue() + ", " +
                "'firstName': '" + this.firstName + "', " +
                "'lastName': '" + this.lastName + "', " +
                "'email': '" + this.email + "'" +
                "}";
    }

    public static Contractor fromCardOwnerDto(CardOwnerDto dto)
    {
        return new Contractor(
                UniqueId.generateWithUUID(),
                dto.firstName,
                dto.lastName,
                dto.email);
    }
}
