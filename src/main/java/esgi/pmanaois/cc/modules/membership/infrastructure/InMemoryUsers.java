package esgi.pmanaois.cc.modules.membership.infrastructure;

import esgi.pmanaois.cc.modules.membership.domain.User;
import esgi.pmanaois.cc.modules.membership.domain.UserId;
import esgi.pmanaois.cc.modules.membership.domain.Users;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

final public class InMemoryUsers implements Users {
    final private Map<UserId, User> data = new ConcurrentHashMap<>();

    @Override
    public Optional<User> findById(UserId id) {
        return Optional.ofNullable(data.get(id));
    }

    @Override
    public void remove(UserId id) {
        this.data.remove(id);
    }

    @Override
    public void save(User user) {
        data.put(user.getId(), user);
    }
}
