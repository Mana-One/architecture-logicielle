package esgi.pmanaois.cc.modules.membership;

import java.util.List;

final public class InvalidUserState extends IllegalStateException {
    public InvalidUserState(String message) {
        super(message);
    }

    public static InvalidUserState fromMessages(List<String> messages) {
        return new InvalidUserState(String.join("\n", messages));
    }
}
