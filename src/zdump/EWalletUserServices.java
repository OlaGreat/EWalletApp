package zdump;

import data.models.Address;
import data.models.User;
import dto.response.DeleteResponse;

import java.util.List;

public class EWalletUserServices implements UserService{
    UserRepository userRepository = new EWalletUserRepository();


    @Override
    public RegisterUserResponse registerNewUser(RegisterUserRequest request) throws UserRegistrationProcessFailedException {
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setUserName(request.getUserName());
        Address address = new Address();
        address.setStreet(request.getStreet());
        address.setState(request.getState());
        address.setHouseNumber(request.getHouseNumber());
        address.setLga(request.getLga());
        user.setAddress(address);
        User savedUser = userRepository.saveUser(user);

        if(savedUser == null) throw new UserRegistrationProcessFailedException("Your onbording process failed");
        RegisterUserResponse registeredUser = new RegisterUserResponse();
        registeredUser.setUserName(savedUser.getUserName());
        registeredUser.setId(savedUser.getId());

        return registeredUser;

    }

    @Override
    public User findById(int id) {
        return userRepository.findUserByUserId(id);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public DeleteResponse deleteUserById(int id) {
        userRepository.deleteUserByUserId(id);
        DeleteResponse deleteResponse = new DeleteResponse();
        deleteResponse.setMessage("Your account has been deleted");
        return deleteResponse;
    }
}
