package esgi.pmanaois.cc;

import esgi.pmanaois.cc.kernel.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class KernelConfiguration {
    @Bean
    public EventDispatcher<Event> eventEventDispatcher() {
        final Map<Class<? extends Event>, List<EventListener<? extends Event>>> listeners = new HashMap<>();
        return new DefaultEventDispatcher(listeners);
    }

    @Bean
    public CommandBus commandBus() {
        final Map<Class<? extends Command>, CommandHandler> handlers = new HashMap<>();
        return new DefaultCommandBus(handlers);
    }

    @Bean
    public QueryBus queryBus() {
        final Map<Class<? extends Query>, QueryHandler> handlers = new HashMap<>();
        return new DefaultQueryBus(handlers);
    }
}
