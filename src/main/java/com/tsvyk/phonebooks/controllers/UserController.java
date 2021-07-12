package com.tsvyk.phonebooks.controllers;

import com.tsvyk.phonebooks.dto.entry.EntryRequest;
import com.tsvyk.phonebooks.dto.entry.EntryResponse;
import com.tsvyk.phonebooks.dto.user.UserRequest;
import com.tsvyk.phonebooks.dto.user.UserResponse;
import com.tsvyk.phonebooks.services.UserService;
import com.tsvyk.phonebooks.utils.MappingUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private MappingUtils mappingUtils;

    @GetMapping("")
    public ResponseEntity<List<UserResponse>> getAllUsers(@RequestParam(required = false) String name) {

        try {

            List<UserResponse> users = userService.getAllUsers(name).
                    stream().map(mappingUtils::mapToUserResponse).collect(Collectors.toList());

            if (users.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(users, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable("userId") long userId) {

        try {

            UserResponse user = mappingUtils.mapToUserResponse(userService.getUserById(userId));

            if (user != null) {
                return new ResponseEntity<>(user, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("")
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest userRequest) {

        try {

            UserResponse newUser = mappingUtils.mapToUserResponse(userService.createUser(userRequest));

            return new ResponseEntity<>(newUser, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable("userId") long userId,
                                                   @RequestBody UserRequest updateUser) {

        try {

            UserResponse newUser = mappingUtils.mapToUserResponse(userService.updateUser(userId, updateUser));

            if (newUser != null) {
                return new ResponseEntity<>(newUser, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("userId") long userId) {

        try {

            userService.deleteUser(userId);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/{userId}/entries")
    public ResponseEntity<EntryResponse> createEntry(@PathVariable("userId") long userId,
                                                     @RequestBody EntryRequest entryRequest) {

        try {

            EntryResponse newEntry = mappingUtils.mapToEntryResponse(userService.createEntry(userId, entryRequest));

            if (newEntry == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(newEntry, HttpStatus.CREATED);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{userId}/entries")
    public ResponseEntity<List<EntryResponse>> getAllEntriesByUserId(@PathVariable(name = "userId") long userId) {

        try {

            List<EntryResponse> entries = userService.getAllEntriesByUserId(userId).
                    stream().map(mappingUtils::mapToEntryResponse).collect(Collectors.toList());

            if (entries.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(entries, HttpStatus.OK);

        } catch (Exception e) {

            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}