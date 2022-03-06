package esgi.pmanaois.cc.modules.project.domain;

import java.util.List;

final public class InvalidProjectState extends IllegalStateException {
    public InvalidProjectState(String message) {
        super(message);
    }

    public static InvalidProjectState fromMessages(List<String> messages) {
        return new InvalidProjectState(String.join("\n", messages));
    }
}
