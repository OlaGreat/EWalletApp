package data.repositories;

import data.models.Transaction;
import enums.TransactionStatus;
import enums.TransactionType;
import utils.AppUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EWalletTransactionRepository implements TransactionRepository {
   private  List<Transaction> transactions = new ArrayList<>();
    @Override
    public Transaction save(Transaction transaction) {
        transactions.add(transaction);
        return transaction;
    }
    @Override
    public Transaction findById(String  id) {
        for(Transaction savedTransaction : transactions){if(Objects.equals(savedTransaction.getTransactionId(), id)){return savedTransaction;}
        }
        return null;
    }

    @Override
    public List<Transaction> findByType(TransactionType transactionType) {
        List<Transaction> matchedStatus = new ArrayList<>();
        for(Transaction savedTransaction : transactions){
            if(savedTransaction.getTransactionType() == transactionType){
                matchedStatus.add(savedTransaction);
            }
        }
        return matchedStatus;
    }

    @Override
    public List<Transaction> findByStatus(TransactionStatus transactionStatus) {
        List<Transaction> matchedStatus = new ArrayList<>();
        for(Transaction savedTransaction : transactions){
            if(savedTransaction.getTransactionStatus() == transactionStatus){
                matchedStatus.add(savedTransaction);
            }
        }
        return matchedStatus;
    }

    @Override
    public List<Transaction> findAll() {
        return transactions;
    }

    @Override
    public List<Transaction> findByDate(LocalDate date) {
        List<Transaction> foundTransaction = new ArrayList<>();
        for (Transaction savedTransaction : transactions){
            if(savedTransaction.getTransactionDate().equals(date)){
                foundTransaction.add(savedTransaction);
            }
        }


        return foundTransaction;
    }

    @Override
    public void deleteById(String  id) {
        Transaction foundTransaction = findById(id);
        transactions.remove(foundTransaction);

    }

}
