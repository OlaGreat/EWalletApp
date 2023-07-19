package zdump;

import data.models.User;
import utils.AppUtils;

import java.util.ArrayList;
import java.util.List;

public class EWalletUserRepository implements UserRepository{
    private List<User>users = new ArrayList<>();

    @Override
    public User saveUser(User user) {
        boolean isSaved = user.getId() > 0;
        if (isSaved) updateUser(user);
        else {
            saveNewUser(user);
        }
        return user;
    }
    private void saveNewUser(User user){
        user.setId(AppUtils.generateId());
        users.add(user);
    }
    private void updateUser(User user){
        User savedUser = findUserByUserId(user.getId());
        users.remove(savedUser);
        users.add(user);
    }

    @Override
    public User findUserByUserId(int id) {
        for(User savedUser : users){if(savedUser.getId() == id) return savedUser;}
        return null;
    }
    private User findByUserName(String userName) {
        for (User savedUser : users) {
            if (savedUser.getUserName().equals(userName)) return savedUser;}
        return null;
    }

    @Override
    public void deleteUserByUserName(String userName) {
        User foundUser = findByUserName(userName);
        users.remove(foundUser);
    }


    @Override
    public List<User> findAll() {
        return users;
    }

    @Override
    public void deleteUserByUserId(int id) {
        User foundUser = null;
        for(User savedUser : users){if(savedUser.getId() == id) foundUser = savedUser;}
        users.remove(foundUser);
    }


    public int getNumberOfRegisteredUser() {
        return users.size();
    }
}
