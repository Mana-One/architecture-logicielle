package esgi.pmanaois.cc.kernel;

public interface Entity<UID extends UniqueId> {
    UID getId();
}
