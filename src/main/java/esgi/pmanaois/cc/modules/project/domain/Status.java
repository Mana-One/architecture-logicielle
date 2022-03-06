package esgi.pmanaois.cc.modules.project.domain;

import java.util.EnumSet;
import java.util.Optional;

public enum Status {
    INPROGRESS("in progress"),
    WAITING("waiting"),
    DONE("done");

    final private String name; 

    private Status(String name) {
        this.name = name; 
    }

    public String getName() {
        return this.name;
    }

    public static Optional<Status> fromString(String name) {
        return EnumSet.allOf(Status.class)
            .stream()
            .filter(r -> r.name.equals(name))
            .findFirst();
    } 
}