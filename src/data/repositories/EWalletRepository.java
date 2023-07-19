package data.repositories;

import data.models.Wallet;
import utils.AppUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EWalletRepository implements WalletRepository{
    List<Wallet> walletList = new ArrayList<>();

    @Override
    public Wallet saveNewWallet(Wallet wallet) {
        wallet.setWalletId(AppUtils.generateId());
        walletList.add(wallet);
        return wallet;
    }

    @Override
    public Wallet findWalletById(int id) {
        Wallet foundWallet = null;
        for(Wallet wallet: walletList){
            if (wallet.getWalletId() == id) {foundWallet = wallet;return foundWallet;}

        }
        return null;
    }

    @Override
    public void deleteWalletById(int id) {
        Wallet foundWallet = findWalletById(id);
        if (foundWallet!=null)walletList.remove(foundWallet);
    }

    @Override
    public List<Wallet> getAllWallet() {
        return walletList;
    }

    @Override
    public Wallet findWalletByUserName(String userName) {
        for(Wallet foundWallet : walletList){
            if (foundWallet.getUserName().equals(userName)) return foundWallet;
        }
        return null;
    }

    @Override
    public Wallet findByUserNameAndPassWord(String userName, String passWord) {
        for (Wallet foundWallet : walletList){
            if (foundWallet.getUserName().equals(userName)&&foundWallet.getPassWord().equals(passWord))return foundWallet;
        }
        return null;
    }

    @Override
    public Optional<Wallet> findByPhoneNumber(String phoneNumber) {
        for (Wallet foundWallet : walletList){
            if (foundWallet.getPhoneNumber().equals(phoneNumber))return Optional.of(foundWallet);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Wallet>  findByEmail(String email) {
        for (Wallet foundWallet : walletList){
            if (foundWallet.getEmail().equals(email))return Optional.of(foundWallet);
        }
        return Optional.empty();
    }

    @Override
    public void updateWallet(Wallet wallet) {
        Wallet foundWallet = findWalletById(wallet.getWalletId());
        walletList.remove(foundWallet);
        walletList.add(wallet);
    }

    @Override
    public Wallet findByAccountNumber(String accountNumber) {
        for (Wallet foundWallet : walletList){
            if (foundWallet.getAccountNumber().equals(accountNumber)) return foundWallet;
        }
        return null;
    }
}
