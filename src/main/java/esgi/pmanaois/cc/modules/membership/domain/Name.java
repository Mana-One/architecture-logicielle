package esgi.pmanaois.cc.modules.membership.domain;

import java.util.Optional;

final public class Name {
    final private String first;
    final private String last;

    private Name(String first, String last) {
        this.first = first;
        this.last = last;
    }

    public static Optional<Name> create(String first, String last) {
        if (first == null || first.length() == 0) {
            return Optional.empty();
        }

        if (last == null || last.length() == 0) {
            return Optional.empty();
        }

        return Optional.of(new Name(first, last));
    }
}
