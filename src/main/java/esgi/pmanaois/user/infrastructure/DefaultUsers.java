package esgi.pmanaois.user.infrastructure;

import esgi.pmanaois.user.domain.User;
import esgi.pmanaois.user.domain.Users;
import esgi.pmanaois.common.UniqueId;

public class DefaultUsers implements Users
{
    @Override
    public User findById(UniqueId id) {
        throw new AssertionError();
    }

    @Override
    public void save(User user) {
        System.out.println("User " + user + " was registered");
    }
}
