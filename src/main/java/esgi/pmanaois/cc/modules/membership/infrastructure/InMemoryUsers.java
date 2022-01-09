package esgi.pmanaois.cc.modules.membership.infrastructure;

import esgi.pmanaois.cc.modules.membership.domain.User;
import esgi.pmanaois.cc.modules.membership.domain.UserEvent;
import esgi.pmanaois.cc.modules.membership.domain.UserId;
import esgi.pmanaois.cc.modules.membership.domain.Users;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

final public class InMemoryUsers implements Users {
    final private Map<UserId, List<UserEvent>> data = new ConcurrentHashMap<>();

    @Override
    public Optional<User> findById(UserId id) {
        List<UserEvent> events = this.data.get(id);
        if (events == null) {
            return Optional.empty();
        }

        User user = User.init(id);
        user.replay(events);
        return Optional.of(user);
    }

    @Override
    public void remove(UserId id) {
        this.data.remove(id);
    }

    @Override
    public void save(User user) {
        List<UserEvent> events = this.data.get(user.getId());
        if (events != null) {
            events.addAll(user.getEvents());
            return;
        }
        this.data.put(user.getId(), user.getEvents());
    }
}
