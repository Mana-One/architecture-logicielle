package esgi.pmanaois.cc.kernel;

import java.util.Map;

public class DefaultQueryBus implements QueryBus {
    private final Map<Class<? extends Query>, QueryHandler> handlers;
    
    public DefaultQueryBus(Map<Class<? extends Query>, QueryHandler> handlers) {
        this.handlers = handlers;
    }

    @Override
    public <TQuery extends Query, R> void addHandler(Class<TQuery> queryC, QueryHandler<TQuery, R> handler) {
        handlers.putIfAbsent(queryC, handler);        
    }

    @Override
    public <TQuery extends Query, R> R send(TQuery query) {
        return dispatch(query);
    }

    private <Q extends Query, R> R dispatch(Q query) {
        final QueryHandler queryHandler = handlers.get(query.getClass());
        if (queryHandler == null) {
            throw new RuntimeException("No such query handler for " + query.getClass().getName());
        }

        return (R) queryHandler.handle(query);
    }
}
