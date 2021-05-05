package com.tsvyk.phonebooks.controllers;

import com.tsvyk.phonebooks.models.Entry;
import com.tsvyk.phonebooks.repositories.EntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("entries")
public class EntryController {

    @Autowired
    private EntryRepository entryRepository;

    @GetMapping("")
    public ResponseEntity<List<Entry>> getAllEntries(@RequestParam(required = false) String number) {

        try {

            List<Entry> entries = null;

            if (number == null) {
                entries = entryRepository.findAll();
            } else{
                entries = entryRepository.findByNumber(number);
            }

            if (entries.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(entries, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{entryId}")
    public ResponseEntity<Entry> getEntryById(@PathVariable(name = "entryId") long entryId) {
        try {
            Optional<Entry> entry = entryRepository.findById(entryId);

            if (entry.isPresent()) {
                return new ResponseEntity<>(entry.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{entryId}")
    public ResponseEntity<Entry> updateEntry(@PathVariable("entryId") long entryId, @RequestBody Entry updateEntry) {

        try {
            Optional<Entry> entry = entryRepository.findById(entryId);
            Entry newEntry = null;

            if (entry.isPresent()) {
                newEntry = entry.get();
                newEntry.setName(updateEntry.getName());
                entryRepository.save(newEntry);
            }

            if (newEntry != null) {
                return new ResponseEntity<>(newEntry, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{entryId}")
    public ResponseEntity<HttpStatus> deleteEntry(@PathVariable("entryId") long entryId) {
        try {
            entryRepository.deleteById(entryId);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
