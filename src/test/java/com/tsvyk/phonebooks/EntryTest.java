//package com.tsvyk.phonebooks;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.*;
//
//import com.tsvyk.phonebooks.dto.entry.EntryRequest;
//import com.tsvyk.phonebooks.dto.entry.EntryResponse;
//import com.tsvyk.phonebooks.exceptions.NoContentException;
//import com.tsvyk.phonebooks.exceptions.NotFoundException;
//import com.tsvyk.phonebooks.models.Entry;
//import com.tsvyk.phonebooks.models.User;
//import com.tsvyk.phonebooks.repositories.EntryRepository;
//import com.tsvyk.phonebooks.repositories.UserRepository;
//import com.tsvyk.phonebooks.services.impl.EntryServiceImpl;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//
//@SpringBootTest
//public class EntryTest {
//
//    @MockBean
//    private EntryRepository entryRepository;
//
//    @MockBean
//    private UserRepository userRepository;
//
//    @Autowired
//    private EntryServiceImpl entryService;
//
//    @Test
//    @DisplayName("Test get all entries")
//    public void testGetAllEntries() throws NoContentException {
//
//        Entry entry1 = new Entry(0, "Jack", "88005553535");
//        Entry entry2 = new Entry(0, "John", "89623822238");
//
//        when(entryRepository.findAll()).thenReturn(Arrays.asList(entry1, entry2));
//
//        List<EntryResponse> entries = entryService.getAllEntries(null);
//
//        assertEquals(2, entries.size());
//        assertEquals("Jack", entries.get(0).getName());
//        assertEquals("John", entries.get(1).getName());
//
//        verify(entryRepository, times(1)).findAll();
//    }
//
//    @Test
//    @DisplayName("Test get entry by id")
//    public void testGetEntryById() throws NotFoundException {
//
//        when(entryRepository.findById(0L)).thenReturn(java.util.Optional.of(new Entry(0, "Jack", "88005553535")));
//
//        EntryResponse entryById = entryService.getEntryById(0L);
//
//        assertEquals("Jack", entryById.getName());
//        verify(entryRepository, times(1)).findById(0L);
//    }
//
//    @Test
//    @DisplayName("Test update entry")
//    public void testUpdateEntry() throws NotFoundException {
//
//        EntryRequest entryRequest = new EntryRequest();
//        entryRequest.setName("John");
//        entryRequest.setNumber("89623822238");
//
//        when(entryRepository.findById(0L)).thenReturn(Optional.of(new Entry(0, "Jack", "88005553535")));
//        when(entryRepository.save(any())).thenReturn(new Entry(0, "John", "89623822238"));
//
//        EntryResponse updated = entryService.updateEntryById(0L, entryRequest);
//
//        assertEquals("John", updated.getName());
//
//        verify(entryRepository, times(2)).findById(0L);
//        verify(entryRepository, times(1)).save(any());
//    }
//
//    @Test
//    @DisplayName("Test delete entry by id")
//    public void testDeleteEntryById() {
//
//        entryService.deleteEntryById(0L);
//
//        verify(entryRepository, times(1)).deleteById(0L);
//    }
//
//    @Test
//    @DisplayName("Test create entry")
//    public void testCreateEntry() throws NotFoundException {
//
//        EntryRequest entryRequest = new EntryRequest();
//        entryRequest.setName("Jack");
//        entryRequest.setNumber("88005553535");
//
//        when(userRepository.findById(0L)).thenReturn(java.util.Optional.of(new User("John")));
//        when(entryRepository.save(any())).thenReturn(new Entry(0, "Jack", "88005553535"));
//
//        EntryResponse created = entryService.createEntry(0L, entryRequest);
//
//        assertEquals("Jack", created.getName());
//        verify(userRepository, times(1)).findById(0L);
//        verify(entryRepository, times(1)).save(any());
//    }
//
//    @Test
//    @DisplayName("Test get all entry by user id")
//    public void testGetAllEntryByUserId() throws NotFoundException {
//
//        Entry entry1 = new Entry(0, "Jack", "88005553535");
//        Entry entry2 = new Entry(0, "John", "89623822238");
//
//        when(entryRepository.findByUserId(0L)).thenReturn(Arrays.asList(entry1, entry2));
//
//        List<EntryResponse> entries = entryService.getAllEntriesByUserId(0L);
//
//        assertEquals(2, entries.size());
//        assertEquals("Jack", entries.get(0).getName());
//        assertEquals("John", entries.get(1).getName());
//
//        verify(entryRepository, times(1)).findByUserId(0L);
//
//    }
//}
