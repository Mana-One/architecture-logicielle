package esgi.pmanaois.domain;

import java.util.List;

public interface EventBus<TEvent extends AppEvent>
{
    void addSubscribers(Class<TEvent> eventClass, List<Subscriber<TEvent>> subscribers);
    void dispatch(TEvent event);
}
