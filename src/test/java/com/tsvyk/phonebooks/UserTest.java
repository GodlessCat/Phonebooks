package com.tsvyk.phonebooks;

import com.tsvyk.phonebooks.dto.address.AddressStreetNumber;
import com.tsvyk.phonebooks.dto.user.UserNameNumber;
import com.tsvyk.phonebooks.dto.user.UserResponse;
import com.tsvyk.phonebooks.exceptions.NoContentException;
import com.tsvyk.phonebooks.exceptions.NotFoundException;
import com.tsvyk.phonebooks.models.Address;
import com.tsvyk.phonebooks.models.User;
import com.tsvyk.phonebooks.repositories.AddressRepository;
import com.tsvyk.phonebooks.repositories.UserRepository;
import com.tsvyk.phonebooks.services.impl.UserServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class UserTest {

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private AddressRepository addressRepository;

    @Autowired
    private UserServiceImpl userService;

    @Test
    @DisplayName("Test find all users")
    void testFindAll() throws NoContentException {

        User user1 = new User("John", "88005553535");
        User user2 = new User("Jack", "88005553536");

        when(userRepository.findAll()).thenReturn(Arrays.asList(user1, user2));

        List<UserResponse> users = userService.getAllUsers(null);

        assertEquals(2, users.size());
        assertEquals("John", users.get(0).getName());
        assertEquals("Jack", users.get(1).getName());

        verify(userRepository, times(1)).findAll();
    }


    @Test
    @DisplayName("Test find user by id")
    void testFindById() throws NotFoundException {

        when(userRepository.findById(0L)).thenReturn(java.util.Optional.of(new User("John", "88005553535")));

        UserResponse userById = userService.getUserById(0L);

        assertEquals("John", userById.getName());
        verify(userRepository, times(1)).findById(0L);
    }

    @Test
    @DisplayName("Test create user")
    public void testCreateUser() throws NotFoundException {

        UserNameNumber userNameNumber = new UserNameNumber("John", "88005553535");

        when(userRepository.save(any())).thenReturn(new User("John", "88005553535"));

        UserNameNumber created = userService.createUser(userNameNumber);

        assertEquals("John", created.getName());
        verify(userRepository, times(1)).save(any());
    }

    @Test
    @DisplayName("Test update user")
    public void testUpdateUser() throws NotFoundException {

        UserNameNumber userNameNumber = new UserNameNumber("John", "88005553535");


        when(userRepository.findById(0L)).thenReturn(java.util.Optional.of(new User("Steve", "88005553536")));
        when(userRepository.save(any())).thenReturn(new User("John", "88005553535"));

        UserResponse updated = userService.updateUserById(0L, userNameNumber);

        assertEquals("John", updated.getName());

        verify(userRepository, times(1)).findById(0L);
        verify(userRepository, times(1)).save(any());
    }

    @Test
    @DisplayName("Test delete user")
    public void testDeleteUser() {

        userService.deleteUserById(0L);

        verify(userRepository, times(1)).deleteById(0L);
    }

    @Test
    @DisplayName("Test add address to user")
    public void testAddAddressToUser() throws NotFoundException {

        User user = new User("Steve", "88005553536");
        Address address = new Address("Lenina", 1);

        when(userRepository.findById(1L)).
                thenReturn(java.util.Optional.of(user));

        when(addressRepository.findById(1L)).
                thenReturn(java.util.Optional.of(address));

        when(userRepository.save(any())).
                thenReturn(user);

        UserResponse userResponse = userService.addAddressToUser(1L, 1L);

        AddressStreetNumber addressStreetNumber = AddressStreetNumber.from(address);

        assertEquals(true, userResponse.getAddresses().contains(addressStreetNumber));


        verify(userRepository, times(1)).findById(1L);
        verify(addressRepository, times(1)).findById(1L);
        verify(userRepository, times(1)).save(any());
    }

    @Test
    @DisplayName("Test delete address from user")
    public void testDeleteAddressFromUser() throws NotFoundException {

        User user = new User("Steve", "88005553536");
        Address address = new Address("Lenina", 1);
        user.addAddress(address);

        when(userRepository.findById(1L)).
                thenReturn(java.util.Optional.of(user));

        when(addressRepository.findById(1L)).
                thenReturn(java.util.Optional.of(address));

        when(userRepository.save(any())).
                thenReturn(user);

        UserResponse userResponse = userService.deleteAddressFromUser(1L, 1L);
        AddressStreetNumber addressStreetNumber = AddressStreetNumber.from(address);

        assertEquals(false, userResponse.getAddresses().contains(addressStreetNumber));

        verify(userRepository, times(1)).findById(1L);
        verify(addressRepository, times(1)).findById(1L);
        verify(userRepository, times(1)).save(any());
    }

}