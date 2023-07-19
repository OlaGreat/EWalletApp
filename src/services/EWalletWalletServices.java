package services;

import data.models.Wallet;
import data.repositories.EWalletRepository;
import data.repositories.EWalletTransactionRepository;
import data.repositories.TransactionRepository;
import data.repositories.WalletRepository;
import dto.request.*;
import dto.response.*;
import exceptions.*;
import mapper.ModelMapper;
import utils.AppUtils;

import java.text.NumberFormat;
import java.util.*;


public class EWalletWalletServices implements WalletServices{
    private final WalletRepository walletRepository = new EWalletRepository();
    private final TransactionRepository transactionRepository = new EWalletTransactionRepository();
    @Override
    public RegisteredWalletResponse registerNewWallet(CreateNewWalletRequest createWalletRequest) throws WalletRegistrationRequestProcessFailedException, UserAlreadyExistException {
        if (isOldUserEmail(createWalletRequest.getEmail()))throw new UserAlreadyExistException("The provided email "+ createWalletRequest.getEmail() + " already exist please login");
        if (isOldUserName(createWalletRequest.getUserName()))throw new UserAlreadyExistException("The selected userName "+ createWalletRequest.getUserName() + " already exist please select a new user name");
        if (isOldUserPhoneNumber(createWalletRequest.getPhoneNumber()))throw new UserAlreadyExistException("The provided phoneNUmber "+ createWalletRequest.getPhoneNumber() + " already exist please login");
        if (!isValidNumber(createWalletRequest.getPhoneNumber())) throw new IllegalArgumentException("The phone number entered is not valid check and enter a valid number ");


        Wallet newWallet = ModelMapper.registerWalletMapper(createWalletRequest);
        newWallet.setAccountNumber(generateAccountNumber(createWalletRequest.getPhoneNumber()));
        Wallet savedWallet = walletRepository.saveNewWallet(newWallet);

        if (savedWallet == null) throw new WalletRegistrationRequestProcessFailedException("Your wallet creation process failed");
        RegisteredWalletResponse walletResponse = new RegisteredWalletResponse();
        walletResponse.setMessage("Welcome " + savedWallet.getUserName() +  " we look forward to serving you well");
        walletResponse.setUserName(savedWallet.getUserName());
        walletResponse.setAccountNumber(savedWallet.getAccountNumber());
        walletResponse.setId(savedWallet.getWalletId());

        return walletResponse;
    }

    @Override
    public Wallet findWalletById(int id) {
        return walletRepository.findWalletById(id);
    }

    @Override
    public DeleteResponse deleteById(int id) {
        walletRepository.deleteWalletById(id);
        DeleteResponse deleteWalletResponse = new DeleteResponse();
        deleteWalletResponse.setMessage("Your wallet as been successfully deleted");
        return deleteWalletResponse;
    }

    @Override
    public List<Wallet> getAllWallet() {
        return walletRepository.getAllWallet();
    }

    @Override
    public TransactionResponse deposit(DepositRequest depositRequest) throws InvalidAccountNumberException {
        double amount = depositRequest.getAmount();
        String accountNumber = depositRequest.getAccountNumber();
        Wallet wallet = findByAccountNumber(accountNumber);
        if (wallet == null) throw new InvalidAccountNumberException("Wrong Account number please check and re-enter your account number");
        double newBalance = wallet.getBalance(wallet.getPin()) + amount;
        wallet.setBalance(newBalance);


        TransactionServices transactionServices = new EWalletTransactionServices();
        CreateTransactionRequest transactionRequest = new CreateTransactionRequest();
        transactionRequest.setAmount(depositRequest.getAmount());
        transactionRequest.setTransactionTimeAndDate(depositRequest.getDate());
        transactionRequest.setTransactionType(depositRequest.getTransactionType());
        transactionRequest.setTransactionStatus(depositRequest.getTransactionStatus());
        transactionRequest.setTransactionId(AppUtils.generateStringId());
        transactionRequest.setWalletId(wallet.getWalletId());
        TransactionResponse savedTransaction = transactionServices.CreateTransaction(transactionRequest);
        wallet.setTransactions(savedTransaction);
        walletRepository.updateWallet(wallet);
        return savedTransaction;
    }

    @Override
    public TransactionResponse withdraw(WithdrawalRequest withdrawalRequest) throws InvalidAccountNumberException, InCorrectPinException {
        String senderAccountNumber = withdrawalRequest.getSenderAccountNumber();
        String receiverAccountNumber = withdrawalRequest.getReceiverAccountNumber();
        double amountToWithdraw = withdrawalRequest.getAmount();
        String pin = withdrawalRequest.getPin();
        Wallet wallet = findByAccountNumber(senderAccountNumber);
        if (wallet == null) throw new InvalidAccountNumberException("Invalid Account Number");
        if (!Objects.equals(wallet.getPin(), pin))throw new InCorrectPinException("Your pin is incorrect please enter the correct pin");
        boolean isValidWithdrawal = wallet.getBalance(pin) >= amountToWithdraw;
        if (isValidWithdrawal){
            double newBalance = wallet.getBalance(pin) - amountToWithdraw;
            wallet.setBalance(newBalance);
        }
        else{
            throw new IllegalArgumentException("Transfer amount cannot be greater than balance, please enter amount less than or equal to your balance");
        }

            TransactionServices transactionServices = new EWalletTransactionServices();
            CreateTransactionRequest transactionRequest = new CreateTransactionRequest();
            transactionRequest.setAmount(withdrawalRequest.getAmount());
            transactionRequest.setTransactionStatus(withdrawalRequest.getTransactionStatus());
            transactionRequest.setTransactionTimeAndDate(withdrawalRequest.getDate());
            transactionRequest.setTransactionId(AppUtils.generateStringId());
            transactionRequest.setWalletId(wallet.getWalletId());
            transactionRequest.setTransactionType(withdrawalRequest.getTransactionType());
            TransactionResponse savedTransaction = transactionServices.CreateTransaction(transactionRequest);
            wallet.setTransactions(savedTransaction);
            walletRepository.updateWallet(wallet);

        return savedTransaction;
    }

    public String checkBalance(String accountNumber, String pin) throws InvalidAccountNumberException, InCorrectPinException {
      Wallet wallet = walletRepository.findByAccountNumber(accountNumber);
      if (wallet == null){ throw  new InvalidAccountNumberException("The account number you entered is invalid, please enter a valid" +
              "account number");
      }
      Locale nigeria = new Locale("en", "NG");
      NumberFormat nigeriaCurrency = NumberFormat.getCurrencyInstance(nigeria);
        return nigeriaCurrency.format(wallet.getBalance(pin));
    }

    @Override
    public List<TransactionResponse> checkTransactions(String accountNumber) throws InvalidAccountNumberException {
       Wallet wallet = walletRepository.findByAccountNumber(accountNumber);
       if (wallet ==  null) throw new InvalidAccountNumberException("You've entered an incorrect accountNumber check and enter Again ");
       List<TransactionResponse> transactions = wallet.getTransactions();
        return transactions;
    }


    @Override
    public Wallet findWalletByUserNameAndPassWord(String userName, String passWord) {
        return walletRepository.findByUserNameAndPassWord(userName,passWord);
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) throws LoginErrorMessage {
        String passWord = loginRequest.getPassWord();
        String userName = loginRequest.getUserName();
        Wallet loggedInWallet = walletRepository.findByUserNameAndPassWord(userName,passWord);
        if (loggedInWallet == null){ throw new LoginErrorMessage("You have supplied an invalid login details please check and correct details");}
        loggedInWallet.setLoggedIn(true);
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setMessage("Welcome "+loggedInWallet.getUserName());
        return loginResponse;
    }

    @Override
    public Wallet findByAccountNumber(String accountNumber) {
        return walletRepository.findByAccountNumber(accountNumber);
    }

    private static String generateAccountNumber(String phoneNumber){
        return phoneNumber.substring(1);}


    private  boolean isOldUserPhoneNumber(String phoneNumber){
        Optional<Wallet> foundWallet = walletRepository.findByPhoneNumber(phoneNumber);
        return foundWallet.isPresent();
    }
    private boolean isOldUserName(String userName){
        Wallet foundWallet = walletRepository.findWalletByUserName(userName);
        return foundWallet != null;
    }
    private  boolean isOldUserEmail(String email){
        Optional<Wallet> foundWallet = walletRepository.findByEmail(email);
        return foundWallet.isPresent();
    }
    private boolean isValidNumber(String phoneNumber){
        if (phoneNumber.length() != 11) return false;
        return  phoneNumber.chars().allMatch(Character::isDigit);
    }



}

