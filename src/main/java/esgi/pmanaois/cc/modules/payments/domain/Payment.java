package esgi.pmanaois.cc.modules.payments.domain;

import java.time.ZonedDateTime;
import java.util.Objects;

import esgi.pmanaois.cc.kernel.Entity;
import esgi.pmanaois.cc.modules.common.Price;

public class Payment implements Entity<PaymentId> {
    final private PaymentId id;
    final private Price price;
    final private String payerId;
    final private String recipientId;
    final private ZonedDateTime dueDate;
    private PaymentStatus status;

    private Payment(PaymentId id, Price price, String payerId, String recipientId, ZonedDateTime dueDate, PaymentStatus status) {
        this.id = Objects.requireNonNull(id);
        this.price = Objects.requireNonNull(price);
        this.payerId = Objects.requireNonNull(payerId);
        this.recipientId = Objects.requireNonNull(recipientId);
        this.dueDate = Objects.requireNonNull(dueDate);
        this.status = Objects.requireNonNull(status);
    }

    public void markAsFulfilled() {
        if (this.status.equals(PaymentStatus.FULFILLED)) {
            throw new IllegalStateException("Cannot fulfill an already-fulfilled payment.");
        }
        this.status = PaymentStatus.FULFILLED;
    }

    public static Payment of(PaymentId id, Price price, String payerId, String recipientId, ZonedDateTime dueDate, PaymentStatus status) {
        return new Payment(id, price, payerId, recipientId, dueDate, status);
    }

    @Override
    public PaymentId getId() {
        return id;
    }

    public Price getPrice() {
        return price;
    }

    public String getPayerId() {
        return payerId;
    }

    public String getRecipientId() {
        return recipientId;
    }

    public ZonedDateTime getDueDate() {
        return dueDate;
    }
}
