package esgi.pmanaois.cc.modules.membership;

import esgi.pmanaois.cc.modules.membership.domain.UserId;

public class NoSuchUser extends RuntimeException {
    public NoSuchUser(String message) {
        super(message);
    }

    public static NoSuchUser withId(UserId id) {
        return new NoSuchUser("No user with id '" + id.getValue().toString() + "'");
    }
}
