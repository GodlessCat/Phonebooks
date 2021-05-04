package com.test.phonebooks.service;

import com.test.phonebooks.entity.Entry;
import com.test.phonebooks.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserEntryService {
    List<User> getAllUsers(String name);

    User createUser(User user);

    Optional<User> getUserById(long userId);

    User updateUser(long userId, User user);

    void deleteUser(long userId);

    Entry createEntry(long userId, Entry entry);

    List<Entry> getAllEntries(String number);

    List<Entry> getAllEntriesByUserId(long userId);

    Optional<Entry> getEntryById(long entryId);

    Entry updateEntry(long entryId, Entry entry);

    void deleteEntry(long entryId);
}
