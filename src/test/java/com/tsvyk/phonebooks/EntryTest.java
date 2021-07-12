package com.tsvyk.phonebooks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import com.tsvyk.phonebooks.dto.entry.EntryRequest;
import com.tsvyk.phonebooks.models.Entry;
import com.tsvyk.phonebooks.repositories.EntryRepository;
import com.tsvyk.phonebooks.services.impl.EntryServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class EntryTest {

    @Mock
    private EntryRepository entryRepository;

    @InjectMocks
    private EntryServiceImpl entryService;


    @Test
    @DisplayName("Test get all entries")
    public void testGetAllEntries() {

        Entry entry1 = new Entry(0, "Jack", "88005553535");
        Entry entry2 = new Entry(0, "John", "89623822238");

        when(entryRepository.findAll()).thenReturn(Arrays.asList(entry1, entry2));

        List<Entry> entries = entryService.getAllEntries(null);

        assertEquals(2, entries.size());
        assertEquals("Jack", entries.get(0).getName());
        assertEquals("John", entries.get(1).getName());

        verify(entryRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Test get entry by id")
    public void testGetEntryById() {

        when(entryRepository.findByEntryId(0L)).thenReturn(new Entry(0, "Jack", "88005553535"));

        Entry entryById = entryRepository.findByEntryId(0L);

        assertEquals("Jack", entryById.getName());
        verify(entryRepository, times(1)).findByEntryId(0L);
    }

    @Test
    @DisplayName("Test update entry")
    public void testUpdateEntry() {

        EntryRequest entryRequest = new EntryRequest();
        entryRequest.setName("John");
        entryRequest.setNumber("89623822238");

        when(entryRepository.findByEntryId(0L)).thenReturn(new Entry(0, "Jack", "88005553535"));
        when(entryRepository.save(any())).thenReturn(new Entry(0, "John", "89623822238"));

        Entry updated = entryService.updateEntry(0L, entryRequest);

        assertEquals("John", updated.getName());

        verify(entryRepository, times(2)).findByEntryId(0L);
        verify(entryRepository, times(1)).save(any());
    }

    @Test
    @DisplayName("Test delete entry by id")
    public void testDeleteEntryById() {

        entryService.deleteEntryById(0L);

        verify(entryRepository, times(1)).deleteById(0L);
    }
}
