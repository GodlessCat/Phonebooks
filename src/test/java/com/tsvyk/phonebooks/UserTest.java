package com.tsvyk.phonebooks;

import com.tsvyk.phonebooks.dto.entry.EntryRequest;
import com.tsvyk.phonebooks.dto.user.UserRequest;
import com.tsvyk.phonebooks.models.Entry;
import com.tsvyk.phonebooks.models.User;
import com.tsvyk.phonebooks.repositories.EntryRepository;
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

    @Autowired
    private UserServiceImpl userService;

    @MockBean
    private EntryRepository entryRepository;

    @Test
    @DisplayName("Test find all users")
    void testFindAll() {

        User user1 = new User("John");
        User user2 = new User("Jack");

        when(userRepository.findAll()).thenReturn(Arrays.asList(user1, user2));

        List<User> users = userService.getAllUsers(null);

        assertEquals(2, users.size());
        assertEquals("John", users.get(0).getName());
        assertEquals("Jack", users.get(1).getName());

        verify(userRepository, times(1)).findAll();
    }


    @Test
    @DisplayName("Test find user by id")
    void testFindById() {

        when(userRepository.findByUserId(0L)).thenReturn(new User("John"));

        User userById = userService.getUserById(0L);

        assertEquals("John", userById.getName());
        verify(userRepository, times(1)).findByUserId(0L);
    }

    @Test
    @DisplayName("Test create user")
    public void testCreateUser() {

        UserRequest userRequest = new UserRequest();
        userRequest.setName("John");

        when(userRepository.save(any())).thenReturn(new User("John"));

        User created = userService.createUser(userRequest);

        assertEquals("John", created.getName());
        verify(userRepository, times(1)).save(any());
    }

    @Test
    @DisplayName("Test update user")
    public void testUpdateUser() {

        UserRequest userRequest = new UserRequest();
        userRequest.setName("John");

        when(userRepository.findByUserId(0L)).thenReturn(new User("Steve"));
        when(userRepository.save(any())).thenReturn(new User("John"));

        User updated = userService.updateUser(0L, userRequest);

        assertEquals("John", updated.getName());

        verify(userRepository, times(2)).findByUserId(0L);
        verify(userRepository, times(1)).save(any());
    }

    @Test
    @DisplayName("Test delete user")
    public void testDeleteUser() {

        userService.deleteUser(0L);

        verify(userRepository, times(1)).deleteById(0L);
    }

    @Test
    @DisplayName("Test create entry")
    public void testCreateEntry() {

        EntryRequest entryRequest = new EntryRequest();
        entryRequest.setName("Jack");
        entryRequest.setNumber("88005553535");

        when(userRepository.findByUserId(0L)).thenReturn(new User("John"));
        when(entryRepository.save(any())).thenReturn(new Entry(0, "Jack", "88005553535"));

        Entry created = userService.createEntry(0L, entryRequest);

        assertEquals("Jack", created.getName());
        verify(userRepository, times(1)).findByUserId(0L);
        verify(entryRepository, times(1)).save(any());
    }

    @Test
    @DisplayName("Test get all entry by user id")
    public void testGetAllEntryByUserId() {

        Entry entry1 = new Entry(0, "Jack", "88005553535");
        Entry entry2 = new Entry(0, "John", "89623822238");
        Entry entry3 = new Entry(1, "John", "89623822238");

        when(entryRepository.findByUserId(0L)).thenReturn(Arrays.asList(entry1, entry2));

        List<Entry> entries = userService.getAllEntriesByUserId(0L);

        assertEquals(2, entries.size());
        assertEquals("Jack", entries.get(0).getName());
        assertEquals("John", entries.get(1).getName());

        verify(entryRepository, times(1)).findByUserId(0L);

    }
}