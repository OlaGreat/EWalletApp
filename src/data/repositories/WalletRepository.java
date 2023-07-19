package data.repositories;

import data.models.Wallet;

import java.util.List;
import java.util.Optional;

public interface WalletRepository {
     Wallet saveNewWallet(Wallet wallet);
     Wallet findWalletById(int id);
     void deleteWalletById(int id);
     List<Wallet> getAllWallet();
     Wallet findWalletByUserName(String userName);
     Wallet findByUserNameAndPassWord(String userName, String passWord);
     Optional<Wallet> findByPhoneNumber(String phoneNumber);
     Optional<Wallet> findByEmail(String email);

     void updateWallet(Wallet wallet);
     Wallet findByAccountNumber(String accountNumber);
}
