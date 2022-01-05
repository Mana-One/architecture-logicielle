package esgi.pmanaois.cc.kernel;

@FunctionalInterface
public interface QueryBus<TQuery extends Query, R> {
    R send(TQuery query);
}