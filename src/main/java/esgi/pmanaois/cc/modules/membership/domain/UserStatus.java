package esgi.pmanaois.cc.modules.membership.domain;

public enum UserStatus {
    AVAILABLE("Available"),
    ASSIGNED("Assigned");

    private final String name;

    private UserStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
