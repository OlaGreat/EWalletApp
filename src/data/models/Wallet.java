package data.models;

import dto.response.TransactionResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Wallet {
    private String firstName;
    private Gender gender;
    private String lastName;
    private String email;
    private int WalletId;
    private String userName;
    private Address address;
    private double balance;
    private String phoneNumber;
    private String accountNumber;
    private String pin;
    private String passWord;
    private boolean isLoggedIng;
    private final List<TransactionResponse> transactions = new ArrayList<>();


    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public boolean isLoggedIng() {
        return isLoggedIng;
    }

    public void setLoggedIn(boolean loggedIng) {
        isLoggedIng = loggedIng;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public void setTransactions(TransactionResponse transactions) {
        this.transactions.add(transactions);
    }

    public List<TransactionResponse> getTransactions() {
        return transactions;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getWalletId() {
        return WalletId;
    }

    public void setWalletId(int walletId) {
        this.WalletId = walletId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public double getBalance(String pin) {
        boolean isInValidPin  = !Objects.equals(this.pin, pin);
        if (isInValidPin) throw new IllegalArgumentException("invalid pin enter the right pin");
        return balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Wallet{");
        sb.append("firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", id=").append(WalletId);
        sb.append(", userName='").append(userName).append('\'');
        sb.append(", address=").append(address);
        sb.append(", balance=").append(balance);
        sb.append(", accountNumber='").append(accountNumber).append('\'');
        sb.append(", pin='").append(pin).append('\'');
        sb.append(", password='").append(passWord).append('\'');
        sb.append('}');
        return sb.toString();
    }
}