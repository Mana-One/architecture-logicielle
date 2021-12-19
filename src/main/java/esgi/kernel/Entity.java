package esgi.kernel;

public interface Entity<UID extends UniqueId> {
    UID id();
}
