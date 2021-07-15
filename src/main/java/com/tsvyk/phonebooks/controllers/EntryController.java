package com.tsvyk.phonebooks.controllers;

import com.tsvyk.phonebooks.exceptions.NoContentException;
import com.tsvyk.phonebooks.dto.entry.EntryRequest;
import com.tsvyk.phonebooks.dto.entry.EntryResponse;
import com.tsvyk.phonebooks.exceptions.NotFoundException;
import com.tsvyk.phonebooks.services.EntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("entries")
public class EntryController {

    private final EntryService entryService;

    @Autowired
    public EntryController(EntryService entryService) {
        this.entryService = entryService;
    }

    @GetMapping
    public ResponseEntity<List<EntryResponse>> getAllEntries(@RequestParam(required = false) String number) {

        try {
            List<EntryResponse> entries = entryService.getAllEntries(number);

            return new ResponseEntity<>(entries, HttpStatus.OK);

        } catch (NoContentException e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{entryId}")
    public ResponseEntity<EntryResponse> getEntryById(@PathVariable(name = "entryId") long entryId) {

        try {
            EntryResponse entry = entryService.getEntryById(entryId);

            return new ResponseEntity<>(entry, HttpStatus.OK);

        } catch (NoContentException e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{entryId}")
    public ResponseEntity<EntryResponse> updateEntry(@PathVariable("entryId") long entryId,
                                                     @RequestBody EntryRequest entryRequest) {

        try {
            EntryResponse newEntry = entryService.updateEntry(entryId, entryRequest);

            return new ResponseEntity<>(newEntry, HttpStatus.OK);

        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{entryId}")
    public ResponseEntity<HttpStatus> deleteEntry(@PathVariable("entryId") long entryId) {

        try {
            entryService.deleteEntryById(entryId);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/{userId}/new")
    public ResponseEntity<EntryResponse> createEntry(@PathVariable("userId") long userId,
                                                     @RequestBody EntryRequest entryRequest) {

        try {

            EntryResponse newEntry = entryService.createEntry(userId, entryRequest);

            return new ResponseEntity<>(newEntry, HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{userId}/all")
    public ResponseEntity<List<EntryResponse>> getAllEntriesByUserId(@PathVariable(name = "userId") long userId) {

        try {

            List<EntryResponse> entries = entryService.getAllEntriesByUserId(userId);

            return new ResponseEntity<>(entries, HttpStatus.OK);

        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}