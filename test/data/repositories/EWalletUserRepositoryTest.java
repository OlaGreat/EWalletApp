package data.repositories;

import data.models.Address;
import data.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import zdump.EWalletUserRepository;
import zdump.UserRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EWalletUserRepositoryTest {
        UserRepository userRepository = new EWalletUserRepository();
        User user;
        User user2;

        @BeforeEach
        void setUp() {
            user = buildUser();
            user2 = buildUser2();
        }
        @Test
        void testThatUserCanBeSave(){
            User savedUser = userRepository.saveUser(user);
            User savedUser2= userRepository.saveUser(user2);
            assertNotNull(savedUser);
            assertNotNull(savedUser2);
            System.out.println(savedUser.getId());
            System.out.println(savedUser2.getId());
        }
        @Test
        void testThatUserCanBeFindById(){
            User savedUser = userRepository.saveUser(user);
            User savedUser2= userRepository.saveUser(user2);
            assertNotNull(savedUser);
            assertNotNull(savedUser2);
            User foundUser = userRepository.findUserByUserId(savedUser.getId());
            assertNotNull(foundUser);
            assertEquals(foundUser,savedUser);
            System.out.println(savedUser.getId());
            System.out.println(savedUser2.getId());
        }
        @Test
        void testAllUserCanBeFetched(){
            User savedUser = userRepository.saveUser(user);
            User savedUser2= userRepository.saveUser(user2);
            assertNotNull(savedUser);
            assertNotNull(savedUser2);
            List<User> userList = userRepository.findAll();
            assertEquals(2,userList.size());
            System.out.println(savedUser.getId());
            System.out.println(savedUser2.getId());

        }
        @Test
        void testThatUserCanBeDeletedById(){
            User savedUser = userRepository.saveUser(user);
            User savedUser2= userRepository.saveUser(user2);
            assertNotNull(savedUser);
            assertNotNull(savedUser2);
            userRepository.deleteUserByUserId(savedUser2.getId());
            List<User> userList = userRepository.findAll();
            assertEquals(1,userList.size());
            System.out.println(savedUser.getId());
            System.out.println(savedUser2.getId());

        }

        private static User buildUser(){
            User user = new User();
            user.setFirstName("Ola");
            user.setLastName("Ola");
            user.setUserName("userName");
            user.setEmail("Email");
            Address address = new Address();
            address.setLga("sabo");
            address.setState("Lagos");
            address.setHouseNumber("6");
            address.setStreet("Yaba");
            user.setAddress(address);

            return user;
        }
        private static User buildUser2(){
            User user = new User();
            user.setFirstName("Bolaji");
            user.setLastName("padi");
            user.setUserName("tye");
            user.setEmail("iiej");
            Address address = new Address();
            address.setLga("sabo");
            address.setState("Lagos");
            address.setHouseNumber("6");
            address.setStreet("Yaba");
            user.setAddress(address);
            return user;
        }
    }

