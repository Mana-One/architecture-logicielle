package esgi.pmanaois.cc.modules.membership.domain;

import java.util.Objects;
import java.util.Optional;

final public class Name {
    final private String first;
    final private String last;

    private Name(String first, String last) {
        this.first = first;
        this.last = last;
    }

    public String getFirst() {
        return first;
    }

    public String getLast() {
        return last;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Name)) return false;
        Name name = (Name) o;
        return Objects.equals(first, name.first) && Objects.equals(last, name.last);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, last);
    }
}
