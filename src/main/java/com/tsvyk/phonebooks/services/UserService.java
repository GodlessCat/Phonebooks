package com.tsvyk.phonebooks.services;

import com.tsvyk.phonebooks.dto.user.UserRequest;
import com.tsvyk.phonebooks.dto.user.UserResponse;
import com.tsvyk.phonebooks.exceptions.NoContentException;
import com.tsvyk.phonebooks.exceptions.NotFoundException;

import java.util.List;

public interface UserService {

    List<UserResponse> getAllUsers(String name) throws NotFoundException, NoContentException;

    UserResponse getUserById(long id) throws NotFoundException;

    UserResponse createUser(UserRequest userRequest);

    UserResponse updateUser(long id, UserRequest userRequest) throws NotFoundException;

    void deleteUser(long id);
}
