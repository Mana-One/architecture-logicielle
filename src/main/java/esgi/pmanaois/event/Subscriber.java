package esgi.pmanaois.event;

import esgi.pmanaois.event.AppEvent;

public interface Subscriber<TEvent extends AppEvent>
{
    void on(TEvent event);
}
