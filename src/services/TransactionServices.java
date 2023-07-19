package services;

import data.models.Transaction;
import dto.request.CreateTransactionRequest;
import dto.response.DeleteResponse;

import dto.response.TransactionResponse;

import java.util.List;

public interface TransactionServices {
TransactionResponse CreateTransaction(CreateTransactionRequest transactionRequest);
TransactionResponse findById(String  id );
DeleteResponse deleteTransaction(String id);
List<Transaction> getAllTransaction();
}
