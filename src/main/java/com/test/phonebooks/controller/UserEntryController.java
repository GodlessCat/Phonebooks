package com.test.phonebooks.controller;

import com.test.phonebooks.entity.Entry;
import com.test.phonebooks.entity.User;
import com.test.phonebooks.service.UserEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserEntryController {

    @Autowired
    private UserEntryService userEntryService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers(@RequestParam(required = false) String name) {
        try {
            List<User> users = userEntryService.getAllUsers(name);

            if (users.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(users, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable("userId") long userId) {
        Optional<User> user = userEntryService.getUserById(userId);

        if (user.isPresent()) {
            return new ResponseEntity<>(user.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        try {
            User newUser = userEntryService.createUser(user);
            return new ResponseEntity<>(newUser, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/users/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable("userId") long userId, @RequestBody User updateUser) {

        User newUser = userEntryService.updateUser(userId, updateUser);

        if (newUser != null) {
            return new ResponseEntity<>(newUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //
    @DeleteMapping("/users/{userId}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("userId") long userId) {
        userEntryService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/users/{userId}/entries")
    public ResponseEntity<Entry> createEntry(@PathVariable("userId") long userId, @RequestBody Entry entry) {
        try {
            Entry newEntry = userEntryService.createEntry(userId, entry);

            if (newEntry == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(newEntry, HttpStatus.CREATED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/entries")
    public ResponseEntity<List<Entry>> getAllEntries(@RequestParam(required = false) String number) {
        try {
            List<Entry> entries = userEntryService.getAllEntries(number);

            if (entries.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(entries, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/users/{user_id}/entries")
    public ResponseEntity<List<Entry>> getAllEntriesByUserId(@PathVariable(name = "user_id") long user_id) {
        try {
            List<Entry> entries = userEntryService.getAllEntriesByUserId(user_id);

            if (entries.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(entries, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/entries/{entry_id}")
    public ResponseEntity<Entry> getEntryById(@PathVariable(name = "entry_id") long entry_id) {
        Optional<Entry> entry = userEntryService.getEntryById(entry_id);

        if (entry.isPresent()) {
            return new ResponseEntity<>(entry.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/entries/{entryId}")
    public ResponseEntity<Entry> updateEntry(@PathVariable("entryId") long entryId, @RequestBody Entry updateEntry) {

        Entry newEntry = userEntryService.updateEntry(entryId, updateEntry);

        if (newEntry != null) {
            return new ResponseEntity<>(newEntry, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/entries/{entryId}")
    public ResponseEntity<HttpStatus> deleteEntry(@PathVariable("entryId") long entryId) {
        userEntryService.deleteEntry(entryId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}