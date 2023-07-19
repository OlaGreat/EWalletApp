package services;

import data.models.Wallet;
import dto.request.DepositRequest;
import dto.request.CreateNewWalletRequest;
import dto.request.LoginRequest;
import dto.request.WithdrawalRequest;
import dto.response.RegisteredWalletResponse;
import dto.response.TransactionResponse;
import exceptions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
class EWalletWalletServicesTest {
  EWalletWalletServices eWalletWalletServices = new EWalletWalletServices();
  CreateNewWalletRequest walletRequest;

    @BeforeEach
    void setUp() {
        walletRequest = buildNewWallet();
    }
    @Test
    void testThatNewWalletRequestCanBeRegistered() throws WalletRegistrationRequestProcessFailedException, UserAlreadyExistException {
        RegisteredWalletResponse savedWallet = eWalletWalletServices.registerNewWallet(walletRequest);
        assertNotNull(savedWallet);

    }
    @Test
    void testThatSavedWalletCanBeFindById() throws WalletRegistrationRequestProcessFailedException, UserAlreadyExistException {
        RegisteredWalletResponse savedWallet = eWalletWalletServices.registerNewWallet(walletRequest);
        assertNotNull(savedWallet);
        Wallet foundWallet = eWalletWalletServices.findWalletById(savedWallet.getId());
        assertNotNull(foundWallet);
        assertEquals(foundWallet.getWalletId(),savedWallet.getId());
    }
    @Test
    void testThatWalletCanBeDeletedById() throws WalletRegistrationRequestProcessFailedException, UserAlreadyExistException {
        RegisteredWalletResponse savedWallet = eWalletWalletServices.registerNewWallet(walletRequest);

        assertNotNull(savedWallet);
        System.out.println(eWalletWalletServices.deleteById(savedWallet.getId()));
        List<Wallet> walletList = eWalletWalletServices.getAllWallet();
        assertEquals(0,walletList.size());
    }
    @Test
    void testThatUserCanLogin() throws WalletRegistrationRequestProcessFailedException, LoginErrorMessage, UserAlreadyExistException {
        RegisteredWalletResponse savedWallet = eWalletWalletServices.registerNewWallet(walletRequest);
        assertNotNull(savedWallet);
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setPassWord(String.valueOf(1234));
        loginRequest.setUserName("Gree3");
        eWalletWalletServices.login(loginRequest);
        Wallet foundWallet = eWalletWalletServices.findWalletByUserNameAndPassWord("Gree3","1234");
        assertNotNull(foundWallet);
        assertTrue(foundWallet.isLoggedIng());
    }
    @Test
    void testThatAWalletCanDeposit() throws WalletRegistrationRequestProcessFailedException, LoginErrorMessage, UserAlreadyExistException, InvalidAccountNumberException {
        RegisteredWalletResponse savedWallet = eWalletWalletServices.registerNewWallet(walletRequest);
        assertNotNull(savedWallet);
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setPassWord(String.valueOf(1234));
        loginRequest.setUserName("Gree3");
        eWalletWalletServices.login(loginRequest);
        DepositRequest depositRequest = new DepositRequest();
        depositRequest.setAmount(50);
        depositRequest.setAccountNumber("8126188203");
        eWalletWalletServices.deposit(depositRequest);
    }
    @Test
    void registerExistingUserEmail() throws UserAlreadyExistException, WalletRegistrationRequestProcessFailedException {
        RegisteredWalletResponse savedWallet = eWalletWalletServices.registerNewWallet(walletRequest);
        assertNotNull(savedWallet);
        CreateNewWalletRequest request = new CreateNewWalletRequest();
        request.setEmail("Ola@gmail.com");
        try {
            eWalletWalletServices.registerNewWallet(request);
        }
        catch (UserAlreadyExistException userAlreadyExistException){
            System.out.println(userAlreadyExistException.getMessage());
        }


    }
    @Test
    void registerExistingUserPhoneNumber() throws UserAlreadyExistException, WalletRegistrationRequestProcessFailedException {
        RegisteredWalletResponse savedWallet = eWalletWalletServices.registerNewWallet(walletRequest);
        assertNotNull(savedWallet);
        CreateNewWalletRequest request = new CreateNewWalletRequest();
        request.setEmail("Olji");
        request.setPhoneNumber("08126188203");
        try {
            eWalletWalletServices.registerNewWallet(request);
        }
        catch (UserAlreadyExistException userAlreadyExistException){
            System.out.println(userAlreadyExistException.getMessage());
        }


    }
    @Test
    void registerExitingUserUserName() throws UserAlreadyExistException, WalletRegistrationRequestProcessFailedException {
        RegisteredWalletResponse savedWallet = eWalletWalletServices.registerNewWallet(walletRequest);
        assertNotNull(savedWallet);
        CreateNewWalletRequest request = new CreateNewWalletRequest();
        request.setEmail("Olji");
        request.setPhoneNumber("126188203");
        request.setUserName("Gree3");

        try {
            eWalletWalletServices.registerNewWallet(request);
        }
        catch (UserAlreadyExistException | WalletRegistrationRequestProcessFailedException userAlreadyExistException){
            System.out.println(userAlreadyExistException.getMessage());
        }
    }
    @Test
    void testThatWalletUserCanWithdraw() throws LoginErrorMessage, UserAlreadyExistException, WalletRegistrationRequestProcessFailedException, InvalidAccountNumberException, InCorrectPinException {
        RegisteredWalletResponse savedWallet = eWalletWalletServices.registerNewWallet(walletRequest);
        assertNotNull(savedWallet);
        System.out.println(savedWallet);
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setPassWord(String.valueOf(1234));
        loginRequest.setUserName("Gree3");
        eWalletWalletServices.login(loginRequest);
        DepositRequest depositRequest = new DepositRequest();
        depositRequest.setAmount(50);
        depositRequest.setAccountNumber("8126188203");
        eWalletWalletServices.deposit(depositRequest);
        WithdrawalRequest request = new WithdrawalRequest();
        request.setAmount(50);
        request.setPin("1111");
        request.setSenderAccountNumber("8126188203");
        request.setReceiverAccountNumber("03y665473");
        eWalletWalletServices.withdraw(request);
        Wallet wallet = eWalletWalletServices.findByAccountNumber(request.getSenderAccountNumber());
        System.out.println(wallet.getBalance("1111"));
        System.out.println(wallet.getTransactions());
    }
    @Test
    void testThatUserCanCheckBalance() throws InvalidAccountNumberException, InCorrectPinException {
        try {
            RegisteredWalletResponse savedWallet = eWalletWalletServices.registerNewWallet(walletRequest);
            assertNotNull(savedWallet);
            LoginRequest loginRequest = new LoginRequest();
            loginRequest.setPassWord(String.valueOf(1234));
            loginRequest.setUserName("Gree3");
            eWalletWalletServices.login(loginRequest);
            DepositRequest depositRequest = new DepositRequest();
            depositRequest.setAmount(50);
            depositRequest.setAccountNumber("8126188203");
            eWalletWalletServices.deposit(depositRequest);
        } catch (WalletRegistrationRequestProcessFailedException | UserAlreadyExistException |
                 LoginErrorMessage loginFailed){
            System.out.println(loginFailed.getMessage());
        }
        String balance = eWalletWalletServices.checkBalance("8126188203","1111");
        assertNotNull(balance);
    }


    @Test
    void testCheckTransactions(){
        try {
            RegisteredWalletResponse savedWallet = eWalletWalletServices.registerNewWallet(walletRequest);
            assertNotNull(savedWallet);

            LoginRequest loginRequest = new LoginRequest();
            loginRequest.setPassWord(String.valueOf(1234));
            loginRequest.setUserName("Gree3");
            eWalletWalletServices.login(loginRequest);

            DepositRequest depositRequest = new DepositRequest();
            depositRequest.setAmount(50);
            depositRequest.setAccountNumber("8126188203");
            eWalletWalletServices.deposit(depositRequest);

            WithdrawalRequest request = new WithdrawalRequest();
            request.setPin("1111");
            request.setAmount(50);
            request.setReceiverAccountNumber("2344859509");
            request.setSenderAccountNumber("8126188203");
            eWalletWalletServices.withdraw(request);

            List<TransactionResponse> transaction = eWalletWalletServices.checkTransactions("8126188203");
            System.out.println(transaction);
            assertEquals(2,transaction.size());

        } catch (WalletRegistrationRequestProcessFailedException | UserAlreadyExistException | InCorrectPinException | InvalidAccountNumberException |
                 LoginErrorMessage loginFailed){
            System.out.println(loginFailed.getMessage());
        }

    }

    private CreateNewWalletRequest buildNewWallet(){
        CreateNewWalletRequest newWalletRequest = new CreateNewWalletRequest();
        newWalletRequest.setEmail("Ola@gmail.com");
        newWalletRequest.setFirstName("Ola");
        newWalletRequest.setPin("1111");
        newWalletRequest.setLastName("Great");
        newWalletRequest.setUserName("Gree3");
        newWalletRequest.setPhoneNumber("08126188203");
        newWalletRequest.setStreet("Sabo");
        newWalletRequest.setState("Lagos Sate");
        newWalletRequest.setHouseNumber("4");
        newWalletRequest.setLga("yaba");
        newWalletRequest.setPassWord("1234");
        newWalletRequest.setGender("MALE");
        return newWalletRequest;
    }
}