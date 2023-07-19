//package data.repositories;
//
//import data.models.Transaction;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//
//import java.time.LocalDate;
//import java.util.List;
//
//import static enums.TransactionStatus.SUCCESSFUL;
//import static enums.TransactionType.CREDIT;
//import static enums.TransactionType.DEBIT;
//import static org.junit.jupiter.api.Assertions.*;
//
//class EWalletTransactionRepositoryTest {
//
//    TransactionRepository transactionRepository = new EWalletTransactionRepository();
//    Transaction transaction;
//    Transaction savedTransaction;
//    @BeforeEach
//    void setUP(){
//        transaction = new Transaction(CREDIT,5000,SUCCESSFUL);
//        savedTransaction = transactionRepository.save(transaction);
//    }
//
//    @Test
//    void saveTransactions() {
//        assertNotNull(savedTransaction);
//        System.out.println(savedTransaction.getTransactionId());
//        assertEquals(savedTransaction.getTransactionId(),savedTransaction.getTransactionId());
//
//
//
//    }
//
//    @Test
//    void findById() {
//      Transaction transaction = new Transaction(CREDIT,5000,SUCCESSFUL);
//     Transaction newSavedTransaction = transactionRepository.save(transaction);
//      assertNotNull(newSavedTransaction);
//
//        assertEquals(savedTransaction,transactionRepository.findById(savedTransaction.getTransactionId()));
//
//    }
//
//    @Test
//    void findByType() {
//        Transaction transaction3 = new Transaction(DEBIT,5000,SUCCESSFUL);
//        transactionRepository.save(transaction3);
//        Transaction transaction2 = new Transaction(CREDIT,5000,SUCCESSFUL);
//        transactionRepository.save(transaction2);
//
//        List<Transaction> foundTransactions = transactionRepository.findByType(CREDIT);
//        assertEquals(2,foundTransactions.size());
//        assertEquals(savedTransaction,foundTransactions.get(0));
//        assertEquals(transaction2,foundTransactions.get(1));
//    }
//
//    @Test
//    void findByStatus() {
//        Transaction transaction = new Transaction(CREDIT,5000,SUCCESSFUL);
//        transactionRepository.save(transaction);
//        Transaction transaction2 = new Transaction(DEBIT,5000,SUCCESSFUL);
//        transactionRepository.save(transaction2);
//        Transaction transaction3 = new Transaction(DEBIT,5000,SUCCESSFUL);
//        transactionRepository.save(transaction3);
//        List<Transaction> statusLIst = transactionRepository.findByStatus(SUCCESSFUL);
//
//        assertEquals(4,statusLIst.size());
//
//    }
//
//    @Test
//    void findAll() {
//        Transaction transaction = new Transaction(CREDIT,5000,SUCCESSFUL);
//        transactionRepository.save(transaction);
//        List<Transaction> transactions = transactionRepository.findAll();
//        assertEquals(transactions.size(),transactionRepository.findAll().size());
//
//    }
//
//    @Test
//    void findByDate() {
//        Transaction transaction = new Transaction(CREDIT,5000,SUCCESSFUL);
//        transactionRepository.save(transaction);
//        assertNotNull(transaction);
//        assertNotNull(savedTransaction);
//
//        LocalDate date = LocalDate.parse("2023-05-16");
//        List<Transaction> transactions = transactionRepository.findByDate(date);
//        System.out.println(transactions.size());
////        assertEquals(transactions.size(),transactionRepository.findAll().size());
//    }
//    @Test
//    void deleteTransactionById(){
//        Transaction transaction = new Transaction(CREDIT,5000,SUCCESSFUL);
//        transactionRepository.save(transaction);
//        assertNotNull(transaction);
//        assertNotNull(savedTransaction);
//        transactionRepository.deleteById(transaction.getTransactionId());
////        assertNotNull(transaction);
//        List<Transaction> transactionList = transactionRepository.findAll();
//        assertEquals(1,transactionList.size());
//    }
//}