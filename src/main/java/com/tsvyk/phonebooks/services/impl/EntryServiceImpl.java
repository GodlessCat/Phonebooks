package com.tsvyk.phonebooks.services.impl;

import com.tsvyk.phonebooks.dto.entry.EntryRequest;
import com.tsvyk.phonebooks.models.Entry;
import com.tsvyk.phonebooks.repositories.EntryRepository;
import com.tsvyk.phonebooks.services.EntryService;
import com.tsvyk.phonebooks.utils.MappingUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntryServiceImpl implements EntryService {

    @Autowired
    EntryRepository entryRepository;

    @Autowired
    MappingUtils mappingUtils;

    @Override
    public List<Entry> getAllEntries(String number) {

        List<Entry> entries;

        if (number == null) {
            entries = entryRepository.findAll();
        } else {
            entries = entryRepository.findByNumber(number);
        }

        return entries;
    }

    @Override
    public Entry getEntryById(long id) {

        return entryRepository.findByEntryId(id);
    }

    @Override
    public Entry updateEntry(long id, EntryRequest entryRequest) {

        Entry entry = entryRepository.findByEntryId(id);

        if (entry != null) {

            entry.setName(entryRequest.getName());
            entry.setNumber(entryRequest.getNumber());

            entryRepository.save(entry);
        }
        return entryRepository.findByEntryId(id);
    }

    @Override
    public void deleteEntryById(long id) {
        entryRepository.deleteById(id);
    }
}