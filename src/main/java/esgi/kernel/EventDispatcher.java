package esgi.kernel;

public interface EventDispatcher<TEvent extends Event> {
    void dispatch(TEvent event);
}
