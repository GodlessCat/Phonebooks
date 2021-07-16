package com.tsvyk.phonebooks.controllers;

import com.tsvyk.phonebooks.dto.address.AddressResponse;
import com.tsvyk.phonebooks.dto.user.UserNameNumber;
import com.tsvyk.phonebooks.dto.user.UserResponse;
import com.tsvyk.phonebooks.exceptions.NoContentException;
import com.tsvyk.phonebooks.exceptions.NotFoundException;
import com.tsvyk.phonebooks.models.User;
import com.tsvyk.phonebooks.services.AddressService;
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

    @PostMapping("/new")
    public ResponseEntity<UserResponse> createUser(@RequestBody UserNameNumber userNameNumber) {

        try {

            UserResponse newUser = userService.createUser(userNameNumber);

            return new ResponseEntity<>(newUser, HttpStatus.CREATED);

        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable("userId") long userId,
                                                   @RequestBody UserNameNumber userNameNumber) {
        try {
            UserResponse user = userService.updateUserById(userId, userNameNumber);

            return new ResponseEntity<>(user, HttpStatus.OK);

        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("userId") long userId) {

        try {
            userService.deleteUserById(userId);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/address{addressId}/all")
    public ResponseEntity<List<UserResponse>> getAllUsersByAddressId(@PathVariable(name = "addressId") long addressId) {

        try {

            List<UserResponse> users = userService.getAllUsersByAddressId(addressId);

            return new ResponseEntity<>(users, HttpStatus.OK);

        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{userId}/addUser/{addressId}")
    public ResponseEntity<UserResponse> addUserToAddress(@PathVariable("userId") long userId,
                                                 @PathVariable("addressId") long addressId) {

        try {
            UserResponse userResponse = userService.addAddressToUser(userId, addressId);

            return new ResponseEntity<>(userResponse, HttpStatus.OK);

        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{addressId}/deleteUser/{userId}")
    public ResponseEntity<UserResponse> deleteUserFromAddress(@PathVariable("addressId") long addressId,
                                                                 @PathVariable("userId") long userId) {

        try {
            UserResponse userResponse = userService.deleteAddressFromUser(userId, addressId);

            return new ResponseEntity<>(userResponse, HttpStatus.OK);

        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}