package zdump;


import data.models.User;
import dto.response.DeleteResponse;

import java.util.List;

public interface UserService {
    RegisterUserResponse registerNewUser(RegisterUserRequest request) throws UserRegistrationProcessFailedException;
    User findById(int id);
    List<User> getAllUser();
    DeleteResponse deleteUserById(int id);


}
