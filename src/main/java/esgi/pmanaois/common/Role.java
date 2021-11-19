package esgi.pmanaois.common;

import java.util.Objects;

public final class Role
{
    public static final Role CONTRACTOR = new Role("Contractor");
    public static final Role TRADESMAN = new Role("Tradesman");

    private String name;

    private Role(String name)
    {
        this.name = Objects.requireNonNull(name);
    }

    public String getName() { return this.name; }

    public static Role of(String name)
    {
        switch(name)
        {
            case "Contractor":
                return Role.CONTRACTOR;
            case "Tradesman":
                return Role.TRADESMAN;
            default:
                throw new IllegalArgumentException("Invalid Role name");
        }
    }
}
