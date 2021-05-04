package com.test.phonebooks.service.impl;

import com.test.phonebooks.entity.Entry;
import com.test.phonebooks.entity.User;
import com.test.phonebooks.repositories.EntryRepository;
import com.test.phonebooks.repositories.UserRepository;
import com.test.phonebooks.service.UserEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserEntryServiceImpl implements UserEntryService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EntryRepository entryRepository;

    @Override
    public List<User> getAllUsers(String name) {
        if (name == null) {
            return userRepository.findAll();
        } else {
            return userRepository.findByNameContaining(name);
        }
    }

    @Override
    public User createUser(User user) {
        return userRepository.saveAndFlush(new User(user.getName()));
    }

    @Override
    public Optional<User> getUserById(long userId) {
        return userRepository.findById(userId);
    }

    @Override
    public User updateUser(long userId, User updateUser) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            User newUser = user.get();
            newUser.setName(updateUser.getName());
            return userRepository.saveAndFlush(newUser);
        } else {
            return null;
        }
    }

    @Override
    public void deleteUser(long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public Entry createEntry(long userId, Entry entry) {
        Optional<User> user = userRepository.findById(userId);

        if (user.isPresent()) {
            return entryRepository.saveAndFlush(new Entry(user.get(), entry.getName(), entry.getNumber()));
        } else {
            return null;
        }
    }

    @Override
    public List<Entry> getAllEntries(String number) {
        if (number == null) {
            return entryRepository.findAll();
        } else {
            return entryRepository.findByNumber(number);
        }
    }

    @Override
    public List<Entry> getAllEntriesByUserId(long userId) {
        return entryRepository.findByUser(userRepository.findById(userId));
    }

    @Override
    public Optional<Entry> getEntryById(long entryId) {
        return entryRepository.findById(entryId);
    }

    @Override
    public Entry updateEntry(long entryId, Entry updateEntry) {

        Optional<Entry> entry = entryRepository.findById(entryId);

        if (entry.isPresent()) {
            Entry newEntry = entry.get();
            newEntry.setName(updateEntry.getName());

            return entryRepository.saveAndFlush(newEntry);
        } else {
            return null;
        }
    }

    @Override
    public void deleteEntry(long entryId) {
        entryRepository.deleteById(entryId);
    }
}
