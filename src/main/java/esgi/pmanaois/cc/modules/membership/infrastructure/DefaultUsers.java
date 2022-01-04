package esgi.pmanaois.cc.modules.membership.infrastructure;

import esgi.pmanaois.cc.modules.membership.domain.User;
import esgi.pmanaois.cc.modules.membership.domain.UserId;
import esgi.pmanaois.cc.modules.membership.domain.Users;

import java.util.Optional;

final public class DefaultUsers implements Users {
    @Override
    public Optional<User> findById(UserId id) {
        return Optional.empty();
    }

    @Override
    public void remove(UserId id) {
        System.out.println("User with " + id + " removed");
    }

    @Override
    public void save(User user) {
        System.out.println("User " + user + " saved.");
    }
}
