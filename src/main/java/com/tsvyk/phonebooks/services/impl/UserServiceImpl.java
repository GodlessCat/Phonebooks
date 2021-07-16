package com.tsvyk.phonebooks.services.impl;

import com.tsvyk.phonebooks.dto.address.AddressResponse;
import com.tsvyk.phonebooks.dto.address.AddressStreetNumber;
import com.tsvyk.phonebooks.dto.user.UserNameNumber;
import com.tsvyk.phonebooks.dto.user.UserRequest;
import com.tsvyk.phonebooks.dto.user.UserResponse;
import com.tsvyk.phonebooks.exceptions.NoContentException;
import com.tsvyk.phonebooks.exceptions.NotFoundException;
import com.tsvyk.phonebooks.models.Address;
import com.tsvyk.phonebooks.models.User;
import com.tsvyk.phonebooks.repositories.AddressRepository;
import com.tsvyk.phonebooks.repositories.UserRepository;
import com.tsvyk.phonebooks.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    private final AddressRepository addressRepository;


    @Autowired
    public UserServiceImpl(UserRepository userRepository, AddressRepository addressRepository) {
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
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
    public UserResponse createUser(UserNameNumber userNameNumber) throws NotFoundException {

        User user = new User();
        user.setName(userNameNumber.getName());
        user.setNumber(userNameNumber.getNumber());
        user.setAddresses(new HashSet<>());

        userRepository.save(user);

        return UserResponse.from(user);
    }

    @Override
    public UserResponse updateUserById(long id, UserNameNumber userNameNumber) throws NotFoundException {

        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty()) {
            throw new NotFoundException();
        }

        user.get().setName(userNameNumber.getName());
        user.get().setNumber(userNameNumber.getNumber());

        userRepository.save(user.get());

        return UserResponse.from(user.get());
    }

    @Override
    public void deleteUserById(long id) {

//        entryRepository.deleteAll(entryRepository.findByUserId(id));
        userRepository.deleteById(id);
    }

    public List<UserResponse> getAllUsersByAddressId(long id) throws NotFoundException {

        Optional<Address> address = addressRepository.findById(id);

        if (address.isEmpty()) {
            throw new NotFoundException();
        }

        Set<User> users = address.get().getUsers();

        if (users.isEmpty()) {
            throw new NotFoundException();
        }

        List<UserResponse> userResponses = new ArrayList<>();

        for (User user : users) {
            userResponses.add(UserResponse.from(user));
        }

        return userResponses;
    }

    @Override
    public UserResponse addAddressToUser(long userId, long addressId) throws NotFoundException {

        Optional<User> user = userRepository.findById(userId);

        Optional<Address> address = addressRepository.findById(addressId);


        if (address.isEmpty() || user.isEmpty()){
            throw new NotFoundException();
        }

        Set<Address> addresses = user.get().getAddresses();
        addresses.add(address.get());

        user.get().setAddresses(addresses);

        return UserResponse.from(userRepository.save(user.get()));
    }

    @Override
    public UserResponse deleteAddressFromUser(long userId, long addressId) throws NotFoundException {

        Optional<User> user = userRepository.findById(userId);

        Optional<Address> address = addressRepository.findById(addressId);


        if (address.isEmpty() || user.isEmpty()){
            throw new NotFoundException();
        }

        Set<Address> addresses = user.get().getAddresses();
        addresses.remove(address.get());

        user.get().setAddresses(addresses);

        return UserResponse.from(userRepository.save(user.get()));
    }

}