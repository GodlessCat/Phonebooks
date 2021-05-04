//package com.test.phonebooks.controller;
//
//import com.test.phonebooks.entity.Entry;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@RestController
//public class EntryController {
//
//
//    @PostMapping("/users/{userId}/entries")
//    public ResponseEntity<Entry> createEntry(@PathVariable("userId") long userId, @RequestBody Entry entry) {
//        try {
//            Entry newEntry = entryRepository
//                    .save(new Entry(userId, entry.getName(), entry.getNumber()));
//            return new ResponseEntity<>(newEntry, HttpStatus.CREATED);
//        } catch (Exception e) {
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//

//}