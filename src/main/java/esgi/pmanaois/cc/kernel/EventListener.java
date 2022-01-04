package esgi.pmanaois.cc.kernel;

public interface EventListener<TEvent extends Event> {
    void listenTo(TEvent event);
}
