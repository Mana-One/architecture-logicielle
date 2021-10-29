package esgi.pmanaois.domain;

public interface Subscriber<TEvent extends AppEvent>
{
    void on(TEvent event);
}
