package zdump;

import data.models.User;

import java.util.List;

public interface UserRepository {
    User saveUser(User use);
    User findUserByUserId(int id);
    void deleteUserByUserName(String userName);
    List<User> findAll();
    void deleteUserByUserId(int id);




}
