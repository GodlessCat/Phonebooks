package com.tsvyk.phonebooks.services;

import com.tsvyk.phonebooks.dto.entry.EntryRequest;
import com.tsvyk.phonebooks.dto.entry.EntryResponse;

import java.util.List;

public interface EntryService {

    List<EntryResponse> getAllEntries(String number);

    EntryResponse getEntryById(long id);

    EntryResponse updateEntry(long id, EntryRequest entryRequest);

    void deleteEntryById(long id);
}
