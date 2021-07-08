package com.tsvyk.phonebooks.services;

import com.tsvyk.phonebooks.dto.entry.EntryRequest;
import com.tsvyk.phonebooks.dto.entry.EntryResponse;
import com.tsvyk.phonebooks.dto.user.UserRequest;
import com.tsvyk.phonebooks.dto.user.UserResponse;

import java.util.List;

public interface UserService {

    List<UserResponse> getAllUsers(String name);

    UserResponse getUserById(long id);

    UserResponse createUser(UserRequest userRequest);

    UserResponse updateUser(long id, UserRequest userRequest);

    void deleteUser(long id);

    EntryResponse createEntry(long id, EntryRequest entryRequest);

    List<EntryResponse> getAllEntriesByUserId(long id);
}
