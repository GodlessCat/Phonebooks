package com.tsvyk.phonebooks.services;

import com.tsvyk.phonebooks.dto.user.UserNameNumber;
import com.tsvyk.phonebooks.dto.user.UserResponse;
import com.tsvyk.phonebooks.exceptions.NoContentException;
import com.tsvyk.phonebooks.exceptions.NotFoundException;

import java.util.List;

public interface UserService {

    List<UserResponse> getAllUsers(String name) throws NotFoundException, NoContentException;

    UserResponse getUserById(long id) throws NotFoundException;

    UserNameNumber createUser(UserNameNumber userNameNumber) throws NotFoundException;

    UserResponse updateUserById(long id, UserNameNumber userNameNumber) throws NotFoundException;

    void deleteUserById(long id);

    UserResponse addAddressToUser(long userId, long addressId) throws NotFoundException;

    UserResponse deleteAddressFromUser(long userId, long addressId) throws NotFoundException;
}
