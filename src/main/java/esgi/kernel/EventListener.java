package esgi.kernel;

public interface EventListener<TEvent extends Event> {
    void listenTo(TEvent event);
}
