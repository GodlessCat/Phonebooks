package com.tsvyk.phonebooks.controllers;

import com.tsvyk.phonebooks.dto.user.UserRequest;
import com.tsvyk.phonebooks.dto.user.UserResponse;
import com.tsvyk.phonebooks.exceptions.NoContentException;
import com.tsvyk.phonebooks.exceptions.NotFoundException;
import com.tsvyk.phonebooks.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers(@RequestParam(required = false) String name) {

        try {
            List<UserResponse> users = userService.getAllUsers(name);

            return new ResponseEntity<>(users, HttpStatus.OK);

        } catch (NoContentException e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable("userId") long userId) {

        try {
            UserResponse user = userService.getUserById(userId);

            return new ResponseEntity<>(user, HttpStatus.OK);

        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("")
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest userRequest) {

        try {
            UserResponse newUser = userService.createUser(userRequest);

            return new ResponseEntity<>(newUser, HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable("userId") long userId,
                                                   @RequestBody UserRequest updateUser) {

        try {
            UserResponse newUser = userService.updateUser(userId, updateUser);

            return new ResponseEntity<>(newUser, HttpStatus.OK);

        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("userId") long userId) {

        try {
            userService.deleteUser(userId);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}