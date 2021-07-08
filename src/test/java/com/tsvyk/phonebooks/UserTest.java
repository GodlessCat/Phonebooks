package com.tsvyk.phonebooks;


import com.tsvyk.phonebooks.dto.user.UserResponse;
import com.tsvyk.phonebooks.models.User;
import com.tsvyk.phonebooks.repositories.UserRepository;
import com.tsvyk.phonebooks.services.impl.UserServiceImpl;
import com.tsvyk.phonebooks.utils.MappingUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class UserTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @InjectMocks
    private MappingUtils mappingUtils;

    @Test
    public void GivenGetAllUsersShouldReturnListOfAllUsers(){

        when(userRepository.findAll()).thenReturn(Arrays.asList(
                new User("John"),
                new User("Jack")
        ));

        List<UserResponse> allUsers = userService.getAllUsers(null);

        assertEquals("John", allUsers.get(0).getName());
        assertEquals("Jack", allUsers.get(1).getName());

    }


//    @Test
//    public void savedUser_Success() {
//
//        UserRequest userRequest = new UserRequest();
//        userRequest.setName("John");
//        userRequest.setUserId(1);
//
//        when(userRepository.save(any(User.class))).thenReturn(new User());
//
//        UserResponse created = userService.createUser(userRequest);
//    }

//    @Test
//    public void customer_exists_in_db_success() {
//        User user = new User();
//        user.setName("John");
//        List<User> userList = new ArrayList<>();
//        userList.add(user);
//
//        // providing knowledge
//        when(userRepository.findAll()).thenReturn(userList);
//
//        List<UserResponse> users = userService.getAllUsers("");
//        assertThat(users.size()).isGreaterThan(0);
//    }

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