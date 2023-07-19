package mapper;

import data.models.Address;
import data.models.Gender;
import data.models.Wallet;
import dto.request.CreateNewWalletRequest;

public class ModelMapper {
    public static Wallet registerWalletMapper (CreateNewWalletRequest request){
        Address address = walletAddressMapper(request);
        Wallet wallet = walletMapper(request,address);

        return wallet;
    }

    private static Wallet walletMapper(CreateNewWalletRequest request,Address address){
        Wallet wallet =  new Wallet();
        Gender gender = Gender.valueOf(request.getGender().toUpperCase());
        wallet.setFirstName(request.getFirstName());
        wallet.setLastName(request.getLastName());
        wallet.setEmail(request.getEmail());
        wallet.setPin(request.getPin());
        wallet.setUserName(request.getUserName());
        wallet.setPhoneNumber(request.getPhoneNumber());
        wallet.setPassWord(request.getPassWord());
        wallet.setGender(gender);
        wallet.setAddress(address);

        return wallet;
    }
    private static Address walletAddressMapper(CreateNewWalletRequest request){
        Address address = new Address();
        address.setLga(request.getLga());
        address.setState(request.getState());
        address.setStreet(request.getStreet());
        address.setHouseNumber(request.getHouseNumber());
        return address;
    }
}
