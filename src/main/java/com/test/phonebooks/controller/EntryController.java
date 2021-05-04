package com.test.phonebooks.controller;

import com.test.phonebooks.entity.Entry;
import com.test.phonebooks.entity.User;
import com.test.phonebooks.repository.EntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class EntryController {

    @Autowired
    private EntryRepository entryRepository;

    @PostMapping("/users/{userId}/entries")
    public ResponseEntity<Entry> createEntry(@PathVariable("userId") long userId, @RequestBody Entry entry) {
        try {
            Entry newEntry = entryRepository
                    .save(new Entry(userId, entry.getName(), entry.getNumber()));
            return new ResponseEntity<>(newEntry, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/entries")
    public ResponseEntity<List<Entry>> getAllEntries(@RequestParam(required = false) String number) {
        try {
            List<Entry> entries = new ArrayList<>();

            if (number == null)
                entryRepository.findAll().forEach(entries::add);
            else
                entryRepository.findByNumber(number).forEach(entries::add);

            if (entries.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(entries, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
