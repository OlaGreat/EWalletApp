package dto.request;


import enums.TransactionStatus;
import enums.TransactionType;

import java.time.LocalDate;

import static enums.TransactionStatus.FAILED;
import static enums.TransactionStatus.SUCCESSFUL;
import static enums.TransactionType.CREDIT;

public class DepositRequest {
    private double amount;
    private String accountNumber;
    private  LocalDate localDate;
    private final TransactionType transactionType = CREDIT;


    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public LocalDate getDate() {
        return localDate = LocalDate.now();
    }

    public double getAmount() {
        return amount;
    }


    public void setAmount(double amount) {
        boolean isValidAmount = amount > 49;
        if(isValidAmount){
            this.amount = amount;}
        else throw new IllegalArgumentException("Deposit amount cannot be less than 50 naira, pls enter a valid amount");
    }

    public TransactionType getTransactionType(){
        return transactionType;
    }
    public TransactionStatus getTransactionStatus(){
        if (getAmount()>49){
            return SUCCESSFUL;
        }
        return FAILED;
    }
}

