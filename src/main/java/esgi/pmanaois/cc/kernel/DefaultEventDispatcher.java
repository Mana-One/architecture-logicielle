package esgi.pmanaois.cc.kernel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DefaultEventDispatcher<TEvent extends Event> implements EventDispatcher<TEvent> {
    private final Map<Class<TEvent>, List<EventListener<TEvent>>> eventListeners;

    public DefaultEventDispatcher(Map<Class<TEvent>, List<EventListener<TEvent>>> eventListeners) {
        this.eventListeners = eventListeners;
    }

    public void addListener(Class<TEvent> eventC, EventListener<TEvent> eventListener) {
        this.eventListeners.putIfAbsent(eventC, new ArrayList<>());
        final List<EventListener<TEvent>> eventListeners = this.eventListeners.get(eventC);
        eventListeners.add(eventListener);
    }

    @Override
    public void dispatch(TEvent event) {
        final List<EventListener<TEvent>> eventListeners = this.eventListeners.get(event.getClass());
        if (eventListeners != null) {
            eventListeners.forEach(e -> e.listenTo(event));
        }
    }
}
