package esgi.pmanaois.cc.kernel;

public interface EventDispatcher<TEvent extends Event> {
    void addListener(Class<TEvent> eventC, EventListener<TEvent> listener);
    void dispatch(TEvent event);
    void registerEvent(Class<TEvent> eventC);
}
