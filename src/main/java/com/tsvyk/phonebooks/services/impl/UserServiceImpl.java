package com.tsvyk.phonebooks.services.impl;

import com.tsvyk.phonebooks.dto.entry.EntryRequest;
import com.tsvyk.phonebooks.dto.user.UserRequest;
import com.tsvyk.phonebooks.models.Entry;
import com.tsvyk.phonebooks.models.User;
import com.tsvyk.phonebooks.repositories.EntryRepository;
import com.tsvyk.phonebooks.repositories.UserRepository;
import com.tsvyk.phonebooks.services.UserService;
import com.tsvyk.phonebooks.utils.MappingUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    EntryRepository entryRepository;

    @Autowired
    MappingUtils mappingUtils;

    @Override
    public List<User> getAllUsers(String name) {

        List<User> users;

        if (name == null) {
            users = userRepository.findAll();
        } else {
            users = userRepository.findByNameContaining(name);
        }


        return users;
    }

    @Override
    public User getUserById(long id) {

        return userRepository.findByUserId(id);
    }

    @Override
    public User createUser(UserRequest userRequest) {

        User newUser = new User();
        newUser.setName(userRequest.getName());

        return userRepository.save(newUser);
    }

    @Override
    public User updateUser(long id, UserRequest userRequest) {

        User user = userRepository.findByUserId(id);

        if (user != null) {

            user.setName(userRequest.getName());

            userRepository.save(user);
        }

        return userRepository.findByUserId(id);
    }

    @Override
    public void deleteUser(long id) {

        entryRepository.deleteAll(entryRepository.findByUserId(id));
        userRepository.deleteById(id);
    }

    @Override
    public Entry createEntry(long userId, EntryRequest entryRequest) {

        User user = userRepository.findByUserId(userId);

        Entry newEntry = null;

        if (user != null) {
            newEntry = entryRepository.save(new Entry(user.getUserId(),
                    entryRequest.getName(),
                    entryRequest.getNumber()));
        }

        return newEntry;
    }

    @Override
    public List<Entry> getAllEntriesByUserId(long id) {

        List<Entry> entries = entryRepository.findByUserId(id);

        return entries;
    }
}