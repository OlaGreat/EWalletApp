package dto.request;

import enums.TransactionStatus;
import enums.TransactionType;

import java.time.LocalDate;

import static enums.TransactionStatus.FAILED;
import static enums.TransactionStatus.SUCCESSFUL;
import static enums.TransactionType.DEBIT;

public class WithdrawalRequest {
    private double amount;
    private String senderAccountNumber;
    private String receiverAccountNumber;
    private LocalDate date;
    private TransactionStatus transactionStatus;
    private TransactionType transactionType = DEBIT;
    private String pin;

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getReceiverAccountNumber() {
        return receiverAccountNumber;
    }

    public void setReceiverAccountNumber(String receiverAccountNumber) {
        this.receiverAccountNumber = receiverAccountNumber;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        boolean isValidAmount = amount > 49;
        if (isValidAmount) this.amount = amount;
        else throw new IllegalArgumentException("Withdrawal amount cannot be less than 50 naira, pls enter a valid amount");
    }

    public String getSenderAccountNumber() {
        return senderAccountNumber;
    }

    public void setSenderAccountNumber(String senderAccountNumber) {
        this.senderAccountNumber = senderAccountNumber;
    }

    public LocalDate getDate() {
        return date = LocalDate.now();
    }


    public TransactionStatus getTransactionStatus() {
        if (getAmount() > 49) {return transactionStatus = SUCCESSFUL;}
        return transactionStatus = FAILED;
    }



    public TransactionType getTransactionType() {
        return transactionType;
    }


}
