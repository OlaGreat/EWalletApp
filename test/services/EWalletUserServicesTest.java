package services;

import data.models.User;
import zdump.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EWalletUserServicesTest {
    UserService userService = new EWalletUserServices();
    RegisterUserRequest registerUserRequest;

    @BeforeEach
    void setUp() {
        registerUserRequest = buildUserRequest();
    }
    @Test
    void testThatUserServiceCanRegisterUser() throws UserRegistrationProcessFailedException {
        RegisterUserResponse savedUser = userService.registerNewUser(registerUserRequest);
        assertNotNull(savedUser);

    }
    @Test
    void testThatUserCanBeFindWithId() throws UserRegistrationProcessFailedException {
        RegisterUserResponse savedUser = userService.registerNewUser(registerUserRequest);
        assertNotNull(savedUser);
        User foundUser = userService.findById(savedUser.getId());
        assertNotNull(foundUser);
        assertEquals(foundUser.getId(),savedUser.getId());

    }
    @Test
    void testThatUserCanBeDeletedById() throws UserRegistrationProcessFailedException {
        RegisterUserResponse savedUser = userService.registerNewUser(registerUserRequest);
        assertNotNull(savedUser);
        userService.deleteUserById(savedUser.getId());
        List<User> userList = userService.getAllUser();
        assertEquals(0,userList.size());

    }
    private static RegisterUserRequest buildUserRequest(){
        RegisterUserRequest registerUserRequest = new RegisterUserRequest();
        registerUserRequest.setUserName("Omotolani");
        registerUserRequest.setEmail("young@gamail.com");
        registerUserRequest.setLastName("sanni");
        registerUserRequest.setFirstName("oma");
        registerUserRequest.setLga("moro");
        registerUserRequest.setState("Kawara");
        registerUserRequest.setStreet("ggdhdlkf");
        registerUserRequest.setHouseNumber("32");
        return registerUserRequest;
    }
}