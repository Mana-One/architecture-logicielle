package esgi.kernel;

import java.util.Map;

public class DefaultQueryBus implements QueryBus {
    private final Map<Class<? extends Query>, QueryHandler> dataMap;
    
    public DefaultQueryBus(Map<Class<? extends Query>, QueryHandler> dataMap) {
        this.dataMap = dataMap;
    }

    @Override
    public <TQuery extends Query, R> R send(TQuery query) {
        return dispatch(query);
    }

    private <Q extends Query, R> R dispatch(Q query) {
        final QueryHandler queryHandler = dataMap.get(query.getClass());
        if (queryHandler == null) {
            throw new RuntimeException("No such query handler for " + query.getClass().getName());
        }

        return (R) queryHandler.handle(query);
    }
}
