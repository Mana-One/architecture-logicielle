package esgi.kernel;

public interface Repository<UID, TEntity> {
    TEntity findById(UID id);
    void remove(UID id);
    void save(TEntity entity);
}
