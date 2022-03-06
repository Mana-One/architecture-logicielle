package esgi.pmanaois.cc.modules.membership.exposition;

import java.util.List;

public class UsersResponse {
    final public List<UserResponse> users;

    public UsersResponse(List<UserResponse> users) {
        this.users = users;
    }
}
