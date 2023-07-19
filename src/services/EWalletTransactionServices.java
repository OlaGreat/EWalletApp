package services;

import data.models.Transaction;
import data.repositories.EWalletTransactionRepository;
import data.repositories.TransactionRepository;
import dto.request.CreateTransactionRequest;
import dto.response.DeleteResponse;
import dto.response.TransactionResponse;

import java.util.List;
import java.util.Random;

public class EWalletTransactionServices implements TransactionServices{
    private final TransactionRepository transactionRepository = new EWalletTransactionRepository();
    @Override
    public TransactionResponse CreateTransaction(CreateTransactionRequest transactionRequest) {
        Transaction transaction = new Transaction();
        transaction.setAmount(transactionRequest.getAmount());
        transaction.setTransactionType(transactionRequest.getTransactionType());
        transaction.setTransactionStatus(transactionRequest.getTransactionStatus());
        transaction.setTransactionDate(transactionRequest.getTransactionTimeAndDate());
        transaction.setWalletId(transactionRequest.getWalletId());
        transaction.setTransactionId(transactionRequest.getTransactionId());
        Transaction savedTransaction = transactionRepository.save(transaction);

        TransactionResponse transactionResponse = new TransactionResponse();
        transactionResponse.setAmount(savedTransaction.getAmount());
        transactionResponse.setTransactionStatus(savedTransaction.getTransactionStatus());
        transactionResponse.setDate(savedTransaction.getTransactionDate());
        transactionResponse.setId(savedTransaction.getTransactionId());
        transactionResponse.setTransactionType(savedTransaction.getTransactionType());

        return transactionResponse;
    }

    @Override
    public TransactionResponse findById(String  id) {
        Transaction foundTransaction = transactionRepository.findById(id);
        if (foundTransaction != null) {
            TransactionResponse transactionResponse = new TransactionResponse();
            transactionResponse.setAmount(foundTransaction.getAmount());
            transactionResponse.setTransactionStatus(foundTransaction.getTransactionStatus());
            transactionResponse.setDate(foundTransaction.getTransactionDate());
            transactionResponse.setTransactionType(foundTransaction.getTransactionType());
            transactionResponse.setId(foundTransaction.getTransactionId());
            return transactionResponse;
        }

        return null;
    }

    @Override
    public DeleteResponse deleteTransaction(String  id) {
        transactionRepository.deleteById(id);
        DeleteResponse deleteResponse = new DeleteResponse();
       deleteResponse.setMessage("Transaction Successful deleted");
       return deleteResponse;

    }

    @Override
    public List<Transaction> getAllTransaction() {
        return transactionRepository.findAll();
    }

}
