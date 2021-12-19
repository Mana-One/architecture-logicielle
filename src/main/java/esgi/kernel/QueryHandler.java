package esgi.kernel;

@FunctionalInterface
public interface QueryHandler<TQuery extends Query, R> {
    R handle(TQuery query);
}
