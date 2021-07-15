package com.tsvyk.phonebooks.services.impl;

import com.tsvyk.phonebooks.exceptions.NoContentException;
import com.tsvyk.phonebooks.dto.entry.EntryRequest;
import com.tsvyk.phonebooks.dto.entry.EntryResponse;
import com.tsvyk.phonebooks.exceptions.NotFoundException;
import com.tsvyk.phonebooks.models.Entry;
import com.tsvyk.phonebooks.models.User;
import com.tsvyk.phonebooks.repositories.EntryRepository;
import com.tsvyk.phonebooks.repositories.UserRepository;
import com.tsvyk.phonebooks.services.EntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EntryServiceImpl implements EntryService {

    private final EntryRepository entryRepository;

    private final UserRepository userRepository;

    @Autowired
    public EntryServiceImpl(EntryRepository entryRepository, UserRepository userRepository) {
        this.entryRepository = entryRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<EntryResponse> getAllEntries(String number) throws NoContentException {

        List<Entry> entries;

        if (number == null) {
            entries = entryRepository.findAll();
        } else {
            entries = entryRepository.findByNumber(number);
        }

        if (entries.isEmpty()) {
            throw new NoContentException();
        }

        List<EntryResponse> entryResponses = new ArrayList<>();

        for (Entry entry : entries) {
            entryResponses.add(EntryResponse.from(entry));
        }

        return entryResponses;
    }

    @Override
    public EntryResponse getEntryById(long id) throws NotFoundException {

        Optional<Entry> entry = entryRepository.findById(id);

        if (entry.isEmpty()) {
            throw new NotFoundException();
        }

        return EntryResponse.from(entry.get());
    }

    @Override
    public EntryResponse updateEntry(long id, EntryRequest entryRequest) throws NotFoundException {

        Optional<Entry> entry = entryRepository.findById(id);

        if (entry.isEmpty()) {
            throw new NotFoundException();
        }

        entry.get().setName(entryRequest.getName());
        entry.get().setNumber(entryRequest.getNumber());

        entryRepository.save(entry.get());

        return EntryResponse.from(entryRepository.findById(id).get());
    }

    @Override
    public void deleteEntryById(long id) {
        entryRepository.deleteById(id);
    }


    @Override
    public EntryResponse createEntry(long userId, EntryRequest entryRequest) throws NotFoundException {

        Optional<User> user = userRepository.findById(userId);

        if (user.isEmpty()) {
            throw new NotFoundException();
        }

        Entry entry = entryRepository.save(new Entry(user.get().getId(),
                entryRequest.getName(),
                entryRequest.getNumber()));

        return EntryResponse.from(entry);
    }

    @Override
    public List<EntryResponse> getAllEntriesByUserId(long id) throws NotFoundException {

        List<Entry> entries = entryRepository.findByUserId(id);

        if (entries.isEmpty()) {
            throw new NotFoundException();
        }

        List<EntryResponse> entryResponses = new ArrayList<>();

        for (Entry entry : entries) {
            entryResponses.add(EntryResponse.from(entry));
        }

        return entryResponses;
    }
}