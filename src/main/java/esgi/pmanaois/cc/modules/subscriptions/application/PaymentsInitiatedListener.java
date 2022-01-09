package esgi.pmanaois.cc.modules.subscriptions.application;

import esgi.pmanaois.cc.kernel.EventListener;
import esgi.pmanaois.cc.modules.common.PaymentsInitiated;
import esgi.pmanaois.cc.modules.subscriptions.domain.Subscription;
import esgi.pmanaois.cc.modules.subscriptions.domain.Subscriptions;

import java.util.List;

final public class PaymentsInitiatedListener implements EventListener<PaymentsInitiated> {
    final private PaymentGateway paymentGateway;
    final private Subscriptions subscriptions;

    public PaymentsInitiatedListener(PaymentGateway paymentGateway, Subscriptions subscriptions) {
        this.paymentGateway = paymentGateway;
        this.subscriptions = subscriptions;
    }

    @Override
    public void listenTo(PaymentsInitiated event) {
        List<Subscription> dueSubscriptions = this.subscriptions.listByDueDate(event.getDueDate());
        dueSubscriptions.forEach(dS -> {
            this.paymentGateway.makePayment(dS.getPrice(), dS.getPaymentMethodId());
            dS.setForNextMonth(event.getDueDate());
            this.subscriptions.save(dS);
        });
        System.out.println("Processed " + dueSubscriptions.size() + " subscriptions.");
    }
}
