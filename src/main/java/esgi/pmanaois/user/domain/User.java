package esgi.pmanaois.user.domain;

import esgi.pmanaois.common.CardOwnerDto;
import esgi.pmanaois.common.Role;
import esgi.pmanaois.common.UniqueId;

import java.util.Objects;

public final class User
{
    private final UniqueId id;
    private String firstName;
    private String lastName;
    private String email;
    private Role role;

    private User(UniqueId id, String firstName, String lastName, String email, Role role)
    {
        this.id = id;
        this.firstName = Objects.requireNonNull(firstName);
        this.lastName = Objects.requireNonNull(lastName);
        this.email = Objects.requireNonNull(email);
        this.role = Objects.requireNonNull(role);
    }

    @Override
    public String toString()
    {
        return "{" +
                "'id': '" + this.id.getValue() + "', " +
                "'firstName': '" + this.firstName + "', " +
                "'lastName': '" + this.lastName + "', " +
                "'email': '" + this.email + "', " +
                "'role': '" + this.role.getName() + "'" +
                "}";
    }

    public static User fromCardOwnerDto(CardOwnerDto dto)
    {
        return new User(
                UniqueId.generateWithUUID(),
                dto.firstName,
                dto.lastName,
                dto.email,
                Role.of(dto.role));
    }
}
