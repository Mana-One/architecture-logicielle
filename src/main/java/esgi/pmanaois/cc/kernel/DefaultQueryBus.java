package esgi.pmanaois.cc.kernel;

import java.util.Map;

public class DefaultQueryBus<TQuery extends Query, R> implements QueryBus<TQuery, R> {
    private final Map<Class<? extends Query>, QueryHandler> dataMap;
    
    public DefaultQueryBus(Map<Class<? extends Query>, QueryHandler> dataMap) {
        this.dataMap = dataMap;
    }

    @Override
    public R send(TQuery query) {
        return dispatch(query);
    }

    private R dispatch(TQuery query) {
        final QueryHandler queryHandler = dataMap.get(query.getClass());
        if (queryHandler == null) {
            throw new RuntimeException("No such query handler for " + query.getClass().getName());
        }

        return (R) queryHandler.handle(query);
    }
}
