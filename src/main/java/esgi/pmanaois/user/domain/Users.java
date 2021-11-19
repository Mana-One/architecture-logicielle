package esgi.pmanaois.user.domain;

import esgi.pmanaois.common.UniqueId;

public interface Users
{
    User findById(UniqueId id);
    void save(User user);
}
