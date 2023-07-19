package services;

import data.models.Wallet;
import dto.request.CreateNewWalletRequest;
import dto.request.LoginRequest;
import dto.request.WithdrawalRequest;
import dto.response.*;
import dto.request.DepositRequest;
import exceptions.*;

import java.util.List;

public interface WalletServices {
    RegisteredWalletResponse registerNewWallet(CreateNewWalletRequest createWalletRequest) throws WalletRegistrationRequestProcessFailedException, UserAlreadyExistException;

    Wallet findWalletById(int id);
    DeleteResponse deleteById(int id);

    List<Wallet> getAllWallet();
    TransactionResponse deposit(DepositRequest depositRequest) throws InvalidAccountNumberException;
    Wallet findWalletByUserNameAndPassWord(String userName, String passWord);
    LoginResponse login(LoginRequest loginRequest) throws LoginErrorMessage;
    Wallet findByAccountNumber(String accountNumber);
    TransactionResponse withdraw(WithdrawalRequest withdrawalRequest) throws InvalidAccountNumberException, InCorrectPinException;
    String  checkBalance(String accountNumber, String pin) throws InvalidAccountNumberException, InCorrectPinException;
    List<TransactionResponse> checkTransactions(String accountNumber) throws InvalidAccountNumberException;
}

