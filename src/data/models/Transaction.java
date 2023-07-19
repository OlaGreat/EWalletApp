package data.models;

import enums.TransactionStatus;
import enums.TransactionType;

import java.time.LocalDate;

public class Transaction {
    private TransactionType transactionType;
    private double amount;
    private TransactionStatus transactionStatus;
    private LocalDate transactionDate;
    private String transactionId;
    private int walletId;

    public int getWalletId() {
        return walletId;
    }

    public void setWalletId(int walletId) {
        this.walletId = walletId;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
    public LocalDate getTransactionDate(){
        return transactionDate = LocalDate.now();
    }

    public TransactionStatus getTransactionStatus() {
        return transactionStatus;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public void setTransactionStatus(TransactionStatus transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String  transactionId) {
        this.transactionId = transactionId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Transaction{");
        sb.append("transactionType=").append(transactionType);
        sb.append(", amount=").append(amount);
        sb.append(", transactionStatus=").append(transactionStatus);
        sb.append(", transactionTimeAndDate=").append(transactionDate);
        sb.append(", transactionId=").append(transactionId);
        sb.append('}');
        return sb.toString();
    }
}
