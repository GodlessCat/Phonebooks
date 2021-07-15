package com.tsvyk.phonebooks.services;

import com.tsvyk.phonebooks.exceptions.NoContentException;
import com.tsvyk.phonebooks.dto.entry.EntryRequest;
import com.tsvyk.phonebooks.dto.entry.EntryResponse;
import com.tsvyk.phonebooks.exceptions.NotFoundException;
import com.tsvyk.phonebooks.models.Entry;

import java.util.List;
import java.util.Optional;

public interface EntryService {

    List<EntryResponse> getAllEntries(String number) throws NoContentException;

    EntryResponse getEntryById(long id) throws NoContentException, NotFoundException;

    EntryResponse updateEntry(long id, EntryRequest entryRequest) throws NoContentException, NotFoundException;

    void deleteEntryById(long id);

    EntryResponse createEntry(long id, EntryRequest entryRequest) throws NotFoundException;

    List<EntryResponse> getAllEntriesByUserId(long id) throws NotFoundException;
}
