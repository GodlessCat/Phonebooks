package com.tsvyk.phonebooks.services;

import com.tsvyk.phonebooks.dto.entry.EntryRequest;
import com.tsvyk.phonebooks.models.Entry;

import java.util.List;

public interface EntryService {

    List<Entry> getAllEntries(String number);

    Entry getEntryById(long id);

    Entry updateEntry(long id, EntryRequest entryRequest);

    void deleteEntryById(long id);
}
