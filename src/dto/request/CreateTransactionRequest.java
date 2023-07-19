package dto.request;

import enums.TransactionStatus;
import enums.TransactionType;

import java.time.LocalDate;

public class CreateTransactionRequest {
    private TransactionType transactionType;
    private double amount;
    private TransactionStatus transactionStatus;
    private LocalDate transactionTimeAndDate;
     private String transactionId;
     private int walletId;

    public int getWalletId() {
        return walletId;
    }

    public void setWalletId(int walletId) {
        this.walletId = walletId;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
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

    public LocalDate getTransactionTimeAndDate() {
        return transactionTimeAndDate;
    }

    public void setTransactionTimeAndDate(LocalDate transactionTimeAndDate) {
        this.transactionTimeAndDate = transactionTimeAndDate;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

}
