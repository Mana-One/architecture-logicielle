package esgi.kernel;

import java.util.Optional;

public interface Repository<UID, TEntity> {
    Optional<TEntity> findById(UID id);
    void remove(UID id);
    void save(TEntity entity);
}
