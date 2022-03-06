package esgi.pmanaois.cc.modules.membership.application;

import java.util.List;
import java.util.Objects;

import esgi.pmanaois.cc.kernel.QueryHandler;
import esgi.pmanaois.cc.modules.membership.domain.User;
import esgi.pmanaois.cc.modules.membership.domain.Users;

public class ListUsersHandler implements QueryHandler<ListUsers, List<User>> {
    final private Users users;

    public ListUsersHandler(Users users) {
        this.users = Objects.requireNonNull(users);
    }

    @Override
    public List<User> handle(ListUsers query) {
        return users.list();
    }
    
}
