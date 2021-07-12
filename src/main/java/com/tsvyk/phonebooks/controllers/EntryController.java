package com.tsvyk.phonebooks.controllers;

import com.tsvyk.phonebooks.dto.entry.EntryRequest;
import com.tsvyk.phonebooks.dto.entry.EntryResponse;
import com.tsvyk.phonebooks.services.EntryService;
import com.tsvyk.phonebooks.utils.MappingUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("entries")
public class EntryController {

    @Autowired
    private EntryService entryService;

    @Autowired
    private MappingUtils mappingUtils;

    @GetMapping("")
    public ResponseEntity<List<EntryResponse>> getAllEntries(@RequestParam(required = false) String number) {

        try {

            List<EntryResponse> entries = entryService.getAllEntries(number).
                    stream().map(mappingUtils::mapToEntryResponse).collect(Collectors.toList());
            ;

            if (entries.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(entries, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{entryId}")
    public ResponseEntity<EntryResponse> getEntryById(@PathVariable(name = "entryId") long entryId) {

        try {
            EntryResponse entry = mappingUtils.mapToEntryResponse(entryService.getEntryById(entryId));

            if (entry != null) {
                return new ResponseEntity<>(entry, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{entryId}")
    public ResponseEntity<EntryResponse> updateEntry(@PathVariable("entryId") long entryId,
                                                     @RequestBody EntryRequest entryRequest) {

        try {
            EntryResponse newEntry = mappingUtils.mapToEntryResponse(entryService.updateEntry(entryId, entryRequest));

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
            entryService.deleteEntryById(entryId);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}