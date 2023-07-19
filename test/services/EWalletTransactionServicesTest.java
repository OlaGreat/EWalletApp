package services;

import data.models.Transaction;
import dto.request.CreateTransactionRequest;
import dto.response.TransactionResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static enums.TransactionStatus.SUCCESSFUL;
import static enums.TransactionType.DEBIT;
import static org.junit.jupiter.api.Assertions.*;

class EWalletTransactionServicesTest {
    TransactionServices transactionServices = new EWalletTransactionServices();
    CreateTransactionRequest transactionRequest;

    @BeforeEach
    void setUp() {
        transactionRequest = buildTransaction();
    }
    @Test
    void testThatTransactionCanBeSave(){
        TransactionResponse savedTransaction = transactionServices.CreateTransaction(transactionRequest);
        assertNotNull(savedTransaction);
    }

    @Test
    void testTransactionCanBeFindById(){
        TransactionResponse savedTransaction = transactionServices.CreateTransaction(transactionRequest);
        assertNotNull(savedTransaction);
        TransactionResponse foundTransaction = transactionServices.findById(savedTransaction.getId());
        assertNotNull(foundTransaction);
    }
    @Test
    void testTransactionCanBeDeleted(){
        TransactionResponse savedTransaction = transactionServices.CreateTransaction(transactionRequest);
        assertNotNull(savedTransaction);
        transactionServices.deleteTransaction(savedTransaction.getId());
        List<Transaction> transactions = transactionServices.getAllTransaction();
        assertEquals(0,transactions.size());
    }

    private CreateTransactionRequest buildTransaction(){
        CreateTransactionRequest transactionRequest = new CreateTransactionRequest();
        transactionRequest.setAmount(7000);
        transactionRequest.setTransactionType(DEBIT);
        transactionRequest.setTransactionStatus(SUCCESSFUL);
        transactionRequest.setTransactionTimeAndDate(LocalDate.parse("2023-01-12"));
        return transactionRequest;
    }
}