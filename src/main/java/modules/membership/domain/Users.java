package modules.membership.domain;

import esgi.kernel.Repository;

final public class Users implements Repository<UserId, User> {
    @Override
    public User findById(UserId id) {
        return null;
    }

    @Override
    public void remove(UserId id) {
        System.out.println("User with id " + id.toString() + " removed.");
    }

    @Override
    public void save(User user) {
        System.out.println("User " + user + " saved.");
    }
}
