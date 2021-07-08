package com.tsvyk.phonebooks.services.impl;

import com.tsvyk.phonebooks.dto.entry.EntryRequest;
import com.tsvyk.phonebooks.dto.entry.EntryResponse;
import com.tsvyk.phonebooks.models.Entry;
import com.tsvyk.phonebooks.repositories.EntryRepository;
import com.tsvyk.phonebooks.services.EntryService;
import com.tsvyk.phonebooks.utils.MappingUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EntryServiceImpl implements EntryService {

    @Autowired
    EntryRepository entryRepository;

    @Autowired
    MappingUtils mappingUtils;

    @Override
    public List<EntryResponse> getAllEntries(String number) {

        List<Entry> entries = null;

        if (number == null) {
            entries = entryRepository.findAll();
        } else{
            entries = entryRepository.findByNumber(number);
        }

        return entries.stream().map(mappingUtils::mapToEntryResponse).collect(Collectors.toList());
    }

    @Override
    public EntryResponse getEntryById(long id) {

        return mappingUtils.mapToEntryResponse(entryRepository.findByEntryId(id).get(0));
    }

    @Override
    public EntryResponse updateEntry(long id, EntryRequest entryRequest) {

        Optional<Entry> entry = entryRepository.findById(id);

        Entry newEntry = null;

        if (entry.isPresent()) {

            newEntry = entry.get();
            newEntry.setName(entryRequest.getName());
            newEntry.setName(entryRequest.getNumber());

            entryRepository.save(newEntry);
        }

        return getEntryById(id);
    }

    @Override
    public void deleteEntryById(long id) {
        entryRepository.deleteById(id);
    }
}