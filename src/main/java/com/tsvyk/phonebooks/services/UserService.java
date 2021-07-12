package com.tsvyk.phonebooks.services;

import com.tsvyk.phonebooks.dto.entry.EntryRequest;

import com.tsvyk.phonebooks.dto.user.UserRequest;
import com.tsvyk.phonebooks.models.Entry;
import com.tsvyk.phonebooks.models.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers(String name);

    User getUserById(long id);

    User createUser(UserRequest userRequest);

    User updateUser(long id, UserRequest userRequest);

    void deleteUser(long id);

    Entry createEntry(long id, EntryRequest entryRequest);

    List<Entry> getAllEntriesByUserId(long id);
}
