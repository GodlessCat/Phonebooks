package com.tsvyk.phonebooks.services.impl;

import com.tsvyk.phonebooks.dto.entry.EntryRequest;
import com.tsvyk.phonebooks.dto.entry.EntryResponse;
import com.tsvyk.phonebooks.dto.user.UserRequest;
import com.tsvyk.phonebooks.dto.user.UserResponse;
import com.tsvyk.phonebooks.models.Entry;
import com.tsvyk.phonebooks.models.User;
import com.tsvyk.phonebooks.repositories.EntryRepository;
import com.tsvyk.phonebooks.repositories.UserRepository;
import com.tsvyk.phonebooks.services.UserService;
import com.tsvyk.phonebooks.utils.MappingUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    EntryRepository entryRepository;

    @Autowired
    MappingUtils mappingUtils;

    @Override
    public List<UserResponse> getAllUsers(String name) {

        List<User> users;

        if (name == null) {
            users = userRepository.findAll();
        } else {
            users = userRepository.findByNameContaining(name);
        }


        return users.stream().map(mappingUtils::mapToUserResponse).collect(Collectors.toList());
    }

    @Override
    public UserResponse getUserById(long id) {

        return mappingUtils.mapToUserResponse(userRepository.findByUserId(id));
    }

    @Override
    public UserResponse createUser(UserRequest userRequest) {

        User newUser = new User();
        newUser.setName(userRequest.getName());

        User resUser = userRepository.save(newUser);

        UserResponse userResponse = mappingUtils.mapToUserResponse(resUser);

        System.out.println(userResponse);

        return userResponse;
    }

    @Override
    public UserResponse updateUser(long id, UserRequest userRequest) {

        Optional<User> user = userRepository.findById(id);

        User newUser = null;

        if (user.isPresent()) {

            newUser = user.get();
            newUser.setName(userRequest.getName());

            userRepository.save(newUser);
        }

        return getUserById(id);
    }

    @Override
    public void deleteUser(long id) {

        entryRepository.deleteAll(entryRepository.findByUserId(id));
        userRepository.deleteById(id);
    }

    @Override
    public EntryResponse createEntry(long userId, EntryRequest entryRequest) {

        Optional<User> user = userRepository.findById(userId);
        Entry newEntry = null;

        if (user.isPresent()) {
            newEntry = entryRepository.saveAndFlush(new Entry(user.get().getUserId(), entryRequest.getName(), entryRequest.getNumber()));
        }

        return mappingUtils.mapToEntryResponse(newEntry);
    }

    @Override
    public List<EntryResponse> getAllEntriesByUserId(long id) {

        List<Entry> entries = entryRepository.findByUserId(id);

        return entries.stream().map(mappingUtils::mapToEntryResponse).collect(Collectors.toList());

    }
}
