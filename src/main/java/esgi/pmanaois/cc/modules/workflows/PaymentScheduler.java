package esgi.pmanaois.cc.modules.workflows;

import esgi.pmanaois.cc.kernel.Clock;
import esgi.pmanaois.cc.kernel.EventDispatcher;
import esgi.pmanaois.cc.modules.common.PaymentsInitiated;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
final public class PaymentScheduler {
    final private Clock clock;
    final private EventDispatcher dispatcher;

    public PaymentScheduler(Clock clock, EventDispatcher dispatcher) {
        this.clock = Objects.requireNonNull(clock);
        this.dispatcher = Objects.requireNonNull(dispatcher);
    }

    @Scheduled(initialDelay = 5000L, fixedRate = 60 * 60 * 1000L)
    public void initiatePayments() {
        this.dispatcher.dispatch(new PaymentsInitiated(this.clock.now()));
    }
}
