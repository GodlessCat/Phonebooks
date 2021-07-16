//package com.tsvyk.phonebooks;
//
//import com.tsvyk.phonebooks.dto.user.UserRequest;
//import com.tsvyk.phonebooks.dto.user.UserResponse;
//import com.tsvyk.phonebooks.exceptions.NoContentException;
//import com.tsvyk.phonebooks.exceptions.NotFoundException;
//import com.tsvyk.phonebooks.models.User;
//import com.tsvyk.phonebooks.repositories.UserRepository;
//import com.tsvyk.phonebooks.services.impl.UserServiceImpl;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//
//import java.util.Arrays;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//
//@SpringBootTest
//public class UserTest {
//
//    @MockBean
//    private UserRepository userRepository;
//
//    @Autowired
//    private UserServiceImpl userService;
//
//    @Test
//    @DisplayName("Test find all users")
//    void testFindAll() throws NoContentException {
//
//        User user1 = new User("John");
//        User user2 = new User("Jack");
//
//        when(userRepository.findAll()).thenReturn(Arrays.asList(user1, user2));
//
//        List<UserResponse> users = userService.getAllUsers(null);
//
//        assertEquals(2, users.size());
//        assertEquals("John", users.get(0).getName());
//        assertEquals("Jack", users.get(1).getName());
//
//        verify(userRepository, times(1)).findAll();
//    }
//
//
//    @Test
//    @DisplayName("Test find user by id")
//    void testFindById() throws NotFoundException {
//
//        when(userRepository.findById(0L)).thenReturn(java.util.Optional.of(new User("John")));
//
//        UserResponse userById = userService.getUserById(0L);
//
//        assertEquals("John", userById.getName());
//        verify(userRepository, times(1)).findById(0L);
//    }
//
//    @Test
//    @DisplayName("Test create user")
//    public void testCreateUser() {
//
//        UserRequest userRequest = new UserRequest();
//        userRequest.setName("John");
//
//        when(userRepository.save(any())).thenReturn(new User("John"));
//
//        UserResponse created = userService.createUser(userRequest);
//
//        assertEquals("John", created.getName());
//        verify(userRepository, times(1)).save(any());
//    }
//
//    @Test
//    @DisplayName("Test update user")
//    public void testUpdateUser() throws NotFoundException {
//
//        UserRequest userRequest = new UserRequest();
//        userRequest.setName("John");
//
//        when(userRepository.findById(0L)).thenReturn(java.util.Optional.of(new User("Steve")));
//        when(userRepository.save(any())).thenReturn(new User("John"));
//
//        UserResponse updated = userService.updateUserById(0L, userRequest);
//
//        assertEquals("John", updated.getName());
//
//        verify(userRepository, times(1)).findById(0L);
//        verify(userRepository, times(1)).save(any());
//    }
//
//    @Test
//    @DisplayName("Test delete user")
//    public void testDeleteUser() {
//
//        userService.deleteUserById(0L);
//
//        verify(userRepository, times(1)).deleteById(0L);
//    }
//
//}