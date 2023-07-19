package dto.response;

import enums.TransactionStatus;
import enums.TransactionType;

import java.time.LocalDate;

public class TransactionResponse {
    private double amount;
    private TransactionStatus transactionStatus;
    private LocalDate date;
    private String id;
    private TransactionType transactionType;

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public TransactionStatus getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(TransactionStatus transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TransactionResponse{");
        sb.append("amount=").append(amount);
        sb.append(", transactionStatus=").append(transactionStatus);
        sb.append(", localDate=").append(date);
        sb.append(", id=").append(id);
        sb.append(",TransactionType =").append(transactionType);
        sb.append('}');
        return sb.toString();
    }
}
