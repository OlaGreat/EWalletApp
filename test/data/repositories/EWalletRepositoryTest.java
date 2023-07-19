package data.repositories;

import data.models.Address;
import data.models.Wallet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class EWalletRepositoryTest {
    WalletRepository walletRepository = new EWalletRepository();
    Wallet wallet;
    Wallet wallet2;

    @BeforeEach
    void setUp() {
        wallet = buildWallet();
        wallet2 = buildWallet2();
    }
    @Test
    void testThatWalletCanBeSave(){
        Wallet savedWallet = walletRepository.saveNewWallet(wallet);
        Wallet savedWallet1 = walletRepository.saveNewWallet(wallet2);
        assertNotNull(savedWallet1);
        assertNotNull(savedWallet);

    }
    @Test
    void testThatSavedWalletCanBeFindById(){
        Wallet savedWallet = walletRepository.saveNewWallet(wallet);
        Wallet savedWallet1 = walletRepository.saveNewWallet(wallet2);
        assertNotNull(savedWallet1);
        assertNotNull(savedWallet);
        Wallet foundWallet = walletRepository.findWalletById(savedWallet.getWalletId());
        assertEquals(foundWallet,savedWallet);
    }
    @Test
    void testThatAllWalletCanBeFind(){
        Wallet savedWallet = walletRepository.saveNewWallet(wallet);
        Wallet savedWallet1 = walletRepository.saveNewWallet(wallet2);
        assertNotNull(savedWallet1);
        assertNotNull(savedWallet);
        List<Wallet> walletList = walletRepository.getAllWallet();
        assertEquals(2,walletList.size());

    }
    @Test
    void testThatWalletCanBeDeleted(){
        Wallet savedWallet = walletRepository.saveNewWallet(wallet);
        Wallet savedWallet1 = walletRepository.saveNewWallet(wallet2);
        assertNotNull(savedWallet1);
        assertNotNull(savedWallet);
        walletRepository.deleteWalletById(savedWallet.getWalletId());
         List<Wallet> walletList = walletRepository.getAllWallet();
         assertEquals(1,walletList.size());
    }
    @Test
    void testThatWalletCanBeFindByUserNameAndPassWord(){
        Wallet savedWallet = walletRepository.saveNewWallet(wallet);
        Wallet savedWallet2 = walletRepository.saveNewWallet(wallet2);
        Wallet foundWallet = walletRepository.findByUserNameAndPassWord("Great","0000");
        assertEquals(foundWallet,savedWallet);
    }
    @Test
    void testThatWalletCanBeFindByUserName(){
        Wallet savedWallet = walletRepository.saveNewWallet(wallet);
        Wallet savedWallet2 = walletRepository.saveNewWallet(wallet2);
        Wallet foundWallet = walletRepository.findWalletByUserName("great");
        assertEquals(foundWallet,savedWallet2);
    }
    @Test
    void testThatWalletCanBeFIndUsingPhoneNumber(){
        Wallet savedWallet = walletRepository.saveNewWallet(wallet);
        Wallet savedWallet1 = walletRepository.saveNewWallet(wallet2);
        assertNotNull(savedWallet1);
        assertNotNull(savedWallet);
        Wallet foundWallet = walletRepository.findByAccountNumber(String.valueOf(8126188203L));
        assertNotNull(foundWallet);
        assertEquals(foundWallet, savedWallet);
    }

    @Test
    void testThatWalletCanBeFindWithPhoneNumber(){
        Wallet savedWallet = walletRepository.saveNewWallet(wallet);
        Wallet savedWallet1 = walletRepository.saveNewWallet(wallet2);
        assertNotNull(savedWallet1);
        assertNotNull(savedWallet);
        Optional<Wallet> foundWallet = walletRepository.findByPhoneNumber(savedWallet.getPhoneNumber());
        assertTrue(foundWallet.isPresent());
    }
    @Test
    void testThatWalletCanBeFindWithEmail(){
        Wallet savedWallet = walletRepository.saveNewWallet(wallet);
        Wallet savedWallet1 = walletRepository.saveNewWallet(wallet2);
        assertNotNull(savedWallet1);
        assertNotNull(savedWallet);
        Optional<Wallet> foundWallet = walletRepository.findByEmail(savedWallet.getEmail());
        assertTrue(foundWallet.isPresent());
        assertEquals(foundWallet.get(),savedWallet);
    }
    @Test
    void testThatWalletCanBeFindWithWrongEmail(){
        Wallet savedWallet = walletRepository.saveNewWallet(wallet);
        Wallet savedWallet1 = walletRepository.saveNewWallet(wallet2);
        assertNotNull(savedWallet1);
        assertNotNull(savedWallet);
        Optional<Wallet> foundWallet = walletRepository.findByEmail("22icom");
        assertFalse(foundWallet.isPresent());
    }

    private Wallet buildWallet(){
        Wallet wallet = new Wallet();
        Address address = new Address();
        address.setLga("Yaba");
        address.setState("Lagos state");
        address.setHouseNumber("5");
        address.setStreet("Sabo");
        wallet.setAddress(address);
        wallet.setPin("1111");
        wallet.setEmail("Ola@gmail.com");
        wallet.setFirstName("Ola");
        wallet.setAccountNumber("8126188203");
        wallet.setUserName("Great");
        wallet.setLastName("Great");
        wallet.setPassWord("0000");
        wallet.setPhoneNumber("08126188203");
        return wallet;
    }
    private Wallet buildWallet2(){
        Wallet wallet = new Wallet();
        Address address = new Address();
        address.setLga("Yaba");
        address.setState("Lagos state");
        address.setHouseNumber("5");
        address.setStreet("Sabo");
        wallet.setAddress(address);
        wallet.setPin("1111");
        wallet.setEmail("Ola@gmail.com");
        wallet.setFirstName("Ola");
        wallet.setAccountNumber("87126188203");
        wallet.setUserName("great");
        wallet.setLastName("Great");
        wallet.setPassWord("1234");
        wallet.setPhoneNumber("0234156828");
        return wallet;
    }
}