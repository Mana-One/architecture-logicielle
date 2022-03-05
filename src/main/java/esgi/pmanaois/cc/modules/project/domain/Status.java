package esgi.pmanaois.cc.modules.membership.domain;

import java.util.EnumSet;
import java.util.Optional;

public enum Role {
    CONTRACTOR("Contractor"),
    TRADESMAN("Tradesman");

    final private String name; 

    private Role(String name) { 
        this.name = name; 
    }

    public String getName() {
        return this.name;
    }

    public static Optional<Role> fromString(String name) {
        return EnumSet.allOf(Role.class)
            .stream()
            .filter(r -> r.name.equals(name))
            .findFirst();
    } 
}