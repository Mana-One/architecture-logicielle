package esgi.pmanaois.event;

import java.util.List;
import java.util.Map;

public final class DefaultEventBus<TEvent extends AppEvent> implements EventBus<TEvent>
{
    private Map<Class<TEvent>, List<Subscriber<TEvent>>> subscriptionMap;

    public DefaultEventBus(Map<Class<TEvent>, List<Subscriber<TEvent>>> subscriptionMap)
    {
        this.subscriptionMap = subscriptionMap;
    }

    @Override
    public void addSubscribers(Class<TEvent> eventClass, List<Subscriber<TEvent>> subscribers)
    {
        this.subscriptionMap.putIfAbsent(eventClass, subscribers);
    }

    @Override
    public void dispatch(TEvent event)
    {
        final var subscribers = this.subscriptionMap.get(event.getClass());
        if (subscribers == null || subscribers.isEmpty())
        {
            throw new IllegalStateException("No subscribers set for " + event.getClass().getSimpleName());
        }
        subscribers.forEach(s -> s.on(event));
    }
}
