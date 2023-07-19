package data.repositories;

import data.models.Transaction;
import enums.TransactionStatus;
import enums.TransactionType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface TransactionRepository {
    Transaction save(Transaction transaction);
    Transaction findById(String id);
    List<Transaction> findByType(TransactionType transactionType);
    List<Transaction> findByStatus(TransactionStatus transactionStatus);
    List<Transaction> findAll();
    List<Transaction> findByDate(LocalDate date);
    void deleteById(String  id);

}
