package esgi.pmanaois.cc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import esgi.pmanaois.cc.kernel.Clock;
import esgi.pmanaois.cc.kernel.EventDispatcher;
import esgi.pmanaois.cc.modules.common.ProjectPaymentsInitiated;
import esgi.pmanaois.cc.modules.payments.application.ProjectPaymentsInitiatedListener;
import esgi.pmanaois.cc.modules.payments.application.SinglePaymentGateway;
import esgi.pmanaois.cc.modules.payments.domain.Payments;
import esgi.pmanaois.cc.modules.payments.infrastructure.DefaultSinglePaymentGateway;
import esgi.pmanaois.cc.modules.payments.infrastructure.InMemoryPayments;

@Configuration
public class PaymentsConfiguration {
    @Autowired
    KernelConfiguration kernelConfiguration;

    @Bean
    SinglePaymentGateway singlePaymentGateway() {
        return new DefaultSinglePaymentGateway();
    }

    @Bean
    Payments payments() {
        return new InMemoryPayments();
    }

    @Bean
    ProjectPaymentsInitiatedListener projectPaymentsInitiatedListener() {
        EventDispatcher dispatcher = this.kernelConfiguration.eventDispatcher();
        ProjectPaymentsInitiatedListener listener = new ProjectPaymentsInitiatedListener(singlePaymentGateway(), payments());

        dispatcher.addListener(ProjectPaymentsInitiated.class, listener);
        return listener;
    }
}
