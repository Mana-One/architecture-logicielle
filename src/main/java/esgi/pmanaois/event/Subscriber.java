package esgi.pmanaois.event;

public interface Subscriber<TEvent extends AppEvent>
{
    void on(TEvent event);
}
