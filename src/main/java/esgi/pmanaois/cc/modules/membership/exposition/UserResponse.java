package esgi.pmanaois.cc.modules.membership.exposition;

public class UserResponse {
    final public String id;
    final public String firstname;
    final public String lastname;
    final public String role;
    final public String status;

    public UserResponse(String id, String firstname, String lastname, String role, String status) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.role = role;
        this.status = status;
    }
}
