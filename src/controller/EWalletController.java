package controller;

import data.models.Wallet;
import dto.request.CreateNewWalletRequest;
import dto.request.DepositRequest;
import dto.request.LoginRequest;
import dto.request.WithdrawalRequest;
import dto.response.LoginResponse;
import dto.response.RegisteredWalletResponse;
import dto.response.TransactionResponse;
import exceptions.*;
import services.EWalletWalletServices;

import java.util.List;

public class EWalletController {
    private final EWalletWalletServices eWalletWalletServices = new EWalletWalletServices();



    public RegisteredWalletResponse register(CreateNewWalletRequest createNewWalletRequest) throws UserAlreadyExistException, WalletRegistrationRequestProcessFailedException {
        return eWalletWalletServices.registerNewWallet(createNewWalletRequest);
    }

    public TransactionResponse deposit(DepositRequest depositRequest) throws InvalidAccountNumberException {
        return eWalletWalletServices.deposit(depositRequest);
    }
    public TransactionResponse transfer(WithdrawalRequest withdrawalRequest) throws InvalidAccountNumberException, InCorrectPinException {
        return eWalletWalletServices.withdraw(withdrawalRequest);
    }
    public String checkBalance(String accountNumber, String pin) throws InvalidAccountNumberException, InCorrectPinException {
        return eWalletWalletServices.checkBalance(accountNumber,pin);
    }
    public LoginResponse login(LoginRequest loginRequest) throws LoginErrorMessage {
        return eWalletWalletServices.login(loginRequest);
    }
    public List<TransactionResponse> checkTransactions(String accountNumber) throws InvalidAccountNumberException {
        return eWalletWalletServices.checkTransactions(accountNumber);
    }
    public Wallet findById(int id){
       return eWalletWalletServices.findWalletById(id);
    }



}
