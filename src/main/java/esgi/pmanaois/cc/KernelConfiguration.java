package esgi.pmanaois.cc;

import esgi.pmanaois.cc.kernel.*;
import esgi.pmanaois.cc.modules.common.PaymentsInitiated;
import esgi.pmanaois.cc.modules.common.ProjectPaymentsInitiated;
import esgi.pmanaois.cc.modules.common.SubscriptionCreated;
import esgi.pmanaois.cc.modules.common.UserRegistered;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class KernelConfiguration {
    @Bean
    public EventDispatcher<Event> eventDispatcher() {
        final Map<Class<? extends Event>, List<EventListener<? extends Event>>> listeners = new HashMap<>();
        DefaultEventDispatcher dispatcher = new DefaultEventDispatcher(listeners);
        dispatcher.registerEvent(PaymentsInitiated.class);
        dispatcher.registerEvent(SubscriptionCreated.class);
        dispatcher.registerEvent(UserRegistered.class);
        dispatcher.registerEvent(ProjectPaymentsInitiated.class);
        return dispatcher;
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

    @Bean
    public Clock clock() {
        return new SystemClock();
    }
}
