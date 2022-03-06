package esgi.pmanaois.cc.modules.payments.application;

import java.util.List;
import java.util.Objects;

import esgi.pmanaois.cc.kernel.EventListener;
import esgi.pmanaois.cc.modules.common.ProjectPaymentsInitiated;
import esgi.pmanaois.cc.modules.payments.domain.Payment;
import esgi.pmanaois.cc.modules.payments.domain.Payments;

public class ProjectPaymentsInitiatedListener implements EventListener<ProjectPaymentsInitiated> {
    final private SinglePaymentGateway gateway;
    final private Payments payments;

    public ProjectPaymentsInitiatedListener(SinglePaymentGateway gateway, Payments payments) {
        this.gateway = Objects.requireNonNull(gateway);
        this.payments = Objects.requireNonNull(payments);
    }

    @Override
    public void listenTo(ProjectPaymentsInitiated event) {
        List<Payment> duePayments = this.payments.listByDueDate(event.getDueDate());
        duePayments.forEach(dP -> {
            this.gateway.makePaymentOnBehalfOf(dP.getPayerId(), dP.getRecipientId(), dP.getPrice());
            dP.markAsFulfilled();
            this.payments.save(dP);
        });
        System.out.println("Processed " + duePayments.size() + " project payment(s).");
    }
    
}
