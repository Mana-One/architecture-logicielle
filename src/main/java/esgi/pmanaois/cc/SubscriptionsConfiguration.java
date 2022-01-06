package esgi.pmanaois.cc;

import esgi.pmanaois.cc.KernelConfiguration;
import esgi.pmanaois.cc.kernel.Clock;
import esgi.pmanaois.cc.kernel.EventDispatcher;
import esgi.pmanaois.cc.modules.common.PaymentsInitiated;
import esgi.pmanaois.cc.modules.common.UserRegistered;
import esgi.pmanaois.cc.modules.subscriptions.application.PaymentGateway;
import esgi.pmanaois.cc.modules.subscriptions.application.PaymentsInitiatedListener;
import esgi.pmanaois.cc.modules.subscriptions.application.UserRegisteredListener;
import esgi.pmanaois.cc.modules.subscriptions.domain.Subscriptions;
import esgi.pmanaois.cc.modules.subscriptions.infrastructure.DefaultPaymentGateway;
import esgi.pmanaois.cc.modules.subscriptions.infrastructure.InMemorySubscriptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SubscriptionsConfiguration {
    @Autowired
    KernelConfiguration kernelConfiguration;

    @Bean
    PaymentGateway paymentGateway() {
        return new DefaultPaymentGateway();
    }

    @Bean
    Subscriptions subscriptions() {
        return new InMemorySubscriptions();
    }

    @Bean
    UserRegisteredListener subscriptionsUserRegisteredListener() {
        Clock clock  = this.kernelConfiguration.clock();
        EventDispatcher dispatcher = this.kernelConfiguration.eventDispatcher();
        UserRegisteredListener listener = new UserRegisteredListener(clock, dispatcher, paymentGateway(), subscriptions());

        dispatcher.addListener(UserRegistered.class, listener);
        return listener;
    }

    @Bean
    PaymentsInitiatedListener subscriptionsPaymentsInitiatedListener() {
        EventDispatcher dispatcher = this.kernelConfiguration.eventDispatcher();
        PaymentsInitiatedListener listener = new PaymentsInitiatedListener(paymentGateway(), subscriptions());
        dispatcher.addListener(PaymentsInitiated.class, listener);

        return listener;
    }
}
