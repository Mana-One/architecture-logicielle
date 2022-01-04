package esgi.pmanaois.cc.kernel;

@FunctionalInterface
public interface QueryHandler<TQuery extends Query, R> {
    R handle(TQuery query);
}
