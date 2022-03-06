package esgi.pmanaois.cc.modules.membership.domain;

import java.util.List;

import esgi.pmanaois.cc.kernel.Repository;

public interface Users extends Repository<UserId, User> {
    List<User> list();
}
