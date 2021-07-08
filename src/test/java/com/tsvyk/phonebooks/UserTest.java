package com.tsvyk.phonebooks;

import com.tsvyk.phonebooks.dto.user.UserRequest;
import com.tsvyk.phonebooks.dto.user.UserResponse;
import com.tsvyk.phonebooks.models.User;
import com.tsvyk.phonebooks.repositories.UserRepository;
import com.tsvyk.phonebooks.services.impl.UserServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class UserTest {

    @Mock
    private UserRepository userRepository;

    @Autowired
    @InjectMocks
    private UserServiceImpl userService;

//    @Test
//    public void GivenGetAllUsersShouldReturnListOfAllUsers(){
//        userRepository.save(user1);
//        //stubbing mock to return specific data
//
//        when(userRepository.findAll()).thenReturn(users);
//        List<UserResponse> userList = userService.getAllUsers("");
//        assertEquals(userList, users);
//
//        verify(userRepository,times(1)).save(user1);
//        verify(userRepository,times(1)).findAll();
//    }

    @Test
    public void savedUser_Success() {
        User user = new User("John");

        UserRequest userRequest = new UserRequest("John");

        when(userRepository.save(any(User.class))).thenReturn(user);

        UserResponse savedUser = userService.createUser(userRequest);
        assertThat(savedUser.getName()).isNotNull();
    }

    @Test
    public void customer_exists_in_db_success() {
        User user = new User();
        user.setName("John");
        List<User> userList = new ArrayList<>();
        userList.add(user);

        // providing knowledge
        when(userRepository.findAll()).thenReturn(userList);

        List<UserResponse> users = userService.getAllUsers("");
        assertThat(users.size()).isGreaterThan(0);
    }

//
//    @Test
//    public void test_showing_no_content_if_no_entries_was_creating() {
//
//        List<EntryResponse> entries = entryService.getAllEntries("8");
//
//        assertThat(entries).isEmpty();
//
//    }
//
//    @Test
//    public void test_showing_no_content_if_no_users_was_creating() {
//        Iterable<User> users = userRepository.findAll();
//
//        assertThat(users).isEmpty();
//    }
//
//    @Test
//    public void test_save_a_new_user() {
//        User user = userRepository.save(new User("John"));
//
//        assertThat(user).hasFieldOrPropertyWithValue("name", "John");
//    }
//
//    @Test
//    public void test_showing_all_users() {
//        User user1 = new User("John");
//        entityManager.persist(user1);
//
//        User user2 = new User("Jack");
//        entityManager.persist(user2);
//
//        Iterable<User> users = userRepository.findAll();
//
//        assertThat(users).hasSize(2).contains(user1, user2);
//    }
//
//    @Test
//    public void test_showing_user_by_id() {
//        User user1 = new User("John");
//        entityManager.persist(user1);
//
//        User user2 = new User("Jack");
//        entityManager.persist(user2);
//
//        User foundUser = userRepository.findById(user2.getUserId()).get();
//
//        assertThat(foundUser).isEqualTo(user2);
//    }
//
//    @Test
//    public void test_showing_users_by_part_of_name() {
//        User user1 = new User("John");
//        entityManager.persist(user1);
//
//        User user2 = new User("Jack");
//        entityManager.persist(user2);
//
//        User user3 = new User("Joe");
//        entityManager.persist(user3);
//
//
//        Iterable<User> users = userRepository.findByNameContaining("Jo");
//
//        assertThat(users).hasSize(2).contains(user1, user3);
//    }
//
//    @Test
//    public void test_update_user_by_id() {
//        User user1 = new User("John");
//        entityManager.persist(user1);
//
//        User updatedUser = new User("Joe");
//
//        User user = userRepository.findById(user1.getUserId()).get();
//        user.setName(updatedUser.getName());
//        userRepository.save(user);
//
//        User checkUser = userRepository.findById(user1.getUserId()).get();
//
//        assertThat(checkUser.getUserId()).isEqualTo(user1.getUserId());
//        assertThat(checkUser.getName()).isEqualTo(user1.getName());
//    }
//
//    @Test
//    public void test_delete_user_by_id() {
//        User user1 = new User("John");
//        entityManager.persist(user1);
//
//        User user2 = new User("Jack");
//        entityManager.persist(user2);
//
//        userRepository.deleteById(user2.getUserId());
//
//        Iterable<User> users = userRepository.findAll();
//
//        assertThat(users).hasSize(1).contains(user1);
//    }
}