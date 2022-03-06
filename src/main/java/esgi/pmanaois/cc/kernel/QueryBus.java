package esgi.pmanaois.cc.kernel;

public interface QueryBus {
    <TQuery extends Query, R> void addHandler(Class<TQuery> queryC, QueryHandler<TQuery, R> handler);
    <TQuery extends Query, R> R send(TQuery query);
}