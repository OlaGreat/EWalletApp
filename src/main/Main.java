package main;

import controller.EWalletController;
import dto.request.CreateNewWalletRequest;
import dto.request.DepositRequest;
import dto.request.LoginRequest;
import dto.request.WithdrawalRequest;
import exceptions.*;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        displayMenu();


    }




















    private static final EWalletController walletController = new EWalletController();

    public static void displayMenu(){
        String menu = """
                          WELCOME TO OLAS BANK
                          PRESS
                          1 -> CREATE ACCOUNT
                          2 -> LOGIN
                          """;

        int userInput = collectStringInputToInt(menu);
        switch (userInput){
            case 1 -> createAccount();
            case 2 -> login();
        }

    }
    public static void createAccount() {
        boolean incorrectInput = true;
        do {
            try {
                String firstName = collectStringInput("Enter your first name");
                String lastName = collectStringInput("Enter your last Name");
                String email = collectStringInput("Enter your email");
                String pin = collectStringInput("Please set your pin");
                String username= collectStringInput("Enter your userName");
                String phoneNumber = collectStringInput("Enter your phoneNumber");
                String passWord = collectStringInput("Enter your password");
                String gender = collectStringInput("Enter your gender");

                CreateNewWalletRequest request = new CreateNewWalletRequest();
                request.setFirstName(firstName);
                request.setLastName(lastName);
                request.setEmail(email);
                request.setPin(pin);
                request.setUserName(username);
                request.setPassWord(passWord);
                request.setPhoneNumber(phoneNumber);
                request.setGender(gender);

                display(walletController.register(request).toString());
                incorrectInput = false;

            }catch (WalletRegistrationRequestProcessFailedException|IllegalArgumentException|NullPointerException|UserAlreadyExistException userAlreadyExist){
                System.err.println(userAlreadyExist.getMessage());
            }
        }while (incorrectInput);
        displayMenu();
    }
    static String loginUserName = "";

    public static void login() {
        boolean incorrectInput = true;
        String userName = "";
       do {
           try {
               userName = collectStringInput("Enter your username");
               String passWord = collectStringInput("Enter your password");

               LoginRequest request = new LoginRequest();
               request.setPassWord(passWord);
               request.setUserName(userName);
               display(walletController.login(request).toString());
               incorrectInput = false;
           }catch (NullPointerException |LoginErrorMessage loginErrorMessage ){
               System.err.println(loginErrorMessage.getMessage());

           }
       }
       while (incorrectInput);
       loginUserName = userName;
       loggedindisplay(loginUserName);

    }
    private static void loggedindisplay(String userName){
        String mainMenu = "Welcome back "+userName+"\n" +
                          "press:\n"  +
                          "1 -> deposit\n"+
                          "2 -> transfer\n"+
                          "3 -> balance\n"+
                          "4 -> transactions\n"+
                          "5 -> logout\n";
        int input = collectStringInputToInt(mainMenu);
        switch (input){
            case 1 -> deposit();
            case 2 -> transfer();
            case 3 -> checkBalance();
            case 4 -> checkTransaction();
            case 5 -> displayMenu();
        }


    }
    private static void deposit(){
        boolean invalidInput = true;
        do {
            try {
                String accountNumber = collectStringInput("Enter your account number");
                double amount = collectStringInputToInt("Enter amount to deposit");

                DepositRequest request = new DepositRequest();
                request.setAccountNumber(accountNumber);
                request.setAmount(amount);

               display(walletController.deposit(request).toString());
                invalidInput = false;
            }catch (NullPointerException|InvalidAccountNumberException|IllegalArgumentException illegalArgumentException){
                System.out.println(illegalArgumentException.getMessage());
            }
        } while (invalidInput);
        loggedindisplay(loginUserName);
    }

    public static void transfer(){
        boolean incorrectInput = true;
        do {
            try {
                String accountNumber = collectStringInput("Enter your account number");
                String receiverAccountNumber = collectStringInput("Enter the beneficiary's account number");
                String pin = collectStringInput("Please enter your pin");
                double amount = collectStringInputToInt("Please enter amount ");

                WithdrawalRequest request = new WithdrawalRequest();
                request.setSenderAccountNumber(accountNumber);
                request.setReceiverAccountNumber(receiverAccountNumber);
                request.setPin(pin);
                request.setAmount(amount);
                display(walletController.transfer(request).toString());
                incorrectInput = false;

            }catch (InvalidAccountNumberException| NullPointerException |InCorrectPinException wrongPinException){
                System.err.println(wrongPinException.getMessage());
            }

        }while (incorrectInput);
        loggedindisplay(loginUserName);

    }
    public static void checkBalance(){
        boolean invalidInput = true;
        do {
            try {
                String accountNumber = collectStringInput("Enter your account number");
                String pin = collectStringInput("please enter your pin");

                display(walletController.checkBalance(accountNumber,pin));
                invalidInput = false;
            }
            catch (NullPointerException|InvalidAccountNumberException|InCorrectPinException incorrectPin){
                System.err.println(incorrectPin.getMessage());
            }
        }while(invalidInput);
        loggedindisplay(loginUserName);
    }

    public static void checkTransaction(){
        boolean invalidInput = true;
       do {
           try {
               String accountNumber = collectStringInput("Enter your account number");
               display(walletController.checkTransactions(accountNumber).toString());
               invalidInput = false;
           }
           catch (NullPointerException|InvalidAccountNumberException wrongAccountNumber){
               System.err.println(wrongAccountNumber.getMessage());
           }
       }
       while (invalidInput);
       loggedindisplay(loginUserName);
    }


    private static String collectStringInput(String prompt){
        return JOptionPane.showInputDialog(prompt);
    }

    private static void display(String prompt){
        JOptionPane.showInputDialog(null,prompt);
    }
    private static int collectStringInputToInt(String input){
        return Integer.parseInt(JOptionPane.showInputDialog(input));
    }


}
