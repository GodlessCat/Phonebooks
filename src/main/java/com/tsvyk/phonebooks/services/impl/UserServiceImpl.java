package com.tsvyk.phonebooks.services.impl;

import com.tsvyk.phonebooks.dto.user.UserRequest;
import com.tsvyk.phonebooks.dto.user.UserResponse;
import com.tsvyk.phonebooks.exceptions.NoContentException;
import com.tsvyk.phonebooks.exceptions.NotFoundException;
import com.tsvyk.phonebooks.models.User;
import com.tsvyk.phonebooks.repositories.EntryRepository;
import com.tsvyk.phonebooks.repositories.UserRepository;
import com.tsvyk.phonebooks.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final EntryRepository entryRepository;


    @Autowired
    public UserServiceImpl(UserRepository userRepository, EntryRepository entryRepository) {
        this.userRepository = userRepository;
        this.entryRepository = entryRepository;
    }

    @Override
    public List<UserResponse> getAllUsers(String name) throws NoContentException {

        List<User> users;

        if (name == null) {
            users = userRepository.findAll();
        } else {
            users = userRepository.findByNameContaining(name);
        }

        if (users.isEmpty()) {
            throw new NoContentException();
        }

        List<UserResponse> userResponses = new ArrayList<>();

        for (User user : users) {
            userResponses.add(UserResponse.from(user));
        }

        return userResponses;
    }

    @Override
    public UserResponse getUserById(long id) throws NotFoundException {

        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty()) {
            throw new NotFoundException();
        }

        return UserResponse.from(user.get());
    }

    @Override
    public UserResponse createUser(UserRequest userRequest) {

        User user = new User();
        user.setName(userRequest.getName());

        userRepository.save(user);

        return UserResponse.from(user);
    }

    @Override
    public UserResponse updateUser(long id, UserRequest userRequest) throws NotFoundException {

        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty()) {
            throw new NotFoundException();
        }

        user.get().setName(userRequest.getName());

        userRepository.save(user.get());

        return UserResponse.from(user.get());
    }

    @Override
    public void deleteUser(long id) {

        entryRepository.deleteAll(entryRepository.findByUserId(id));
        userRepository.deleteById(id);
    }
}