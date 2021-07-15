package com.tsvyk.phonebooks.services.impl;

import com.tsvyk.phonebooks.dto.user.UserRequest;
import com.tsvyk.phonebooks.dto.user.UserResponse;
import com.tsvyk.phonebooks.exceptions.NoContentException;
import com.tsvyk.phonebooks.exceptions.NotFoundException;
import com.tsvyk.phonebooks.models.User;
import com.tsvyk.phonebooks.repositories.EntryRepository;
import com.tsvyk.phonebooks.repositories.UserRepository;
import com.tsvyk.phonebooks.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final EntryRepository entryRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, EntryRepository entryRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.entryRepository = entryRepository;
        this.modelMapper = modelMapper;
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
            userResponses.add(modelMapper.map(user, UserResponse.class));
        }

        return userResponses;
    }

    @Override
    public UserResponse getUserById(long id) throws NotFoundException {

        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty()) {
            throw new NotFoundException();
        }

        return modelMapper.map(user.get(), UserResponse.class);
    }

    @Override
    public UserResponse createUser(UserRequest userRequest) {

        User newUser = new User();
        newUser.setName(userRequest.getName());

        return modelMapper.map(userRepository.save(newUser), UserResponse.class);
    }

    @Override
    public UserResponse updateUser(long id, UserRequest userRequest) throws NotFoundException {

        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty()) {
            throw new NotFoundException();
        }

        user.get().setName(userRequest.getName());

        return modelMapper.map(userRepository.save(user.get()), UserResponse.class);
    }

    @Override
    public void deleteUser(long id) {

        entryRepository.deleteAll(entryRepository.findByUserId(id));
        userRepository.deleteById(id);
    }
}