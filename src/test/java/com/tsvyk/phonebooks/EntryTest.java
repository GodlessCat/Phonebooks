package com.tsvyk.phonebooks;

import static org.assertj.core.api.Assertions.assertThat;

import com.tsvyk.phonebooks.dto.entry.EntryRequest;
import com.tsvyk.phonebooks.dto.entry.EntryResponse;

import com.tsvyk.phonebooks.models.Entry;
import com.tsvyk.phonebooks.repositories.EntryRepository;
import com.tsvyk.phonebooks.services.impl.EntryServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class EntryTest {

    @Mock
    private EntryRepository entryRepository;

    @Autowired
    @InjectMocks
    private EntryServiceImpl entryService;
    private Entry entry1;
    private Entry entry2;
    List<Entry> entries;

    @BeforeEach
    public void setUp() {
        entries = new ArrayList<>();
        entry1 = new Entry(1, "Jack","88005553535");
        entry2 = new Entry(2, "John","89623822238");
        entries.add(entry1);
        entries.add(entry2);
    }

    @AfterEach
    public void tearDown() {
        entry1 = entry2 = null;
        entries = null;
    }

    @Test
    public void test_showing_no_content_if_no_entries_was_creating() {

        List<EntryResponse> entries = entryService.getAllEntries("8");

        assertThat(entries).isEmpty();

    }


//
//    @Test
//    public void test_save_a_new_entry() {
//
//        UserResponse user1 = userService.createUser(new UserRequest("John"));
//
//        EntryResponse entry = userService.createEntry(user1.getUserId(), new EntryRequest("Jack", "+79123456789"));
//
//        assertThat(entry).hasFieldOrPropertyWithValue("name", "Jack");
//        assertThat(entry).hasFieldOrPropertyWithValue("number", "+79123456789");
//        assertThat(entry).hasFieldOrPropertyWithValue("userId", user1.getUserId());
//
//    }
//
//    @Test
//    public void test_showing_entry_by_id() {
//        Entry entry1 = new Entry(1, "Jack", "+79123456789");
//        entityManager.persist(entry1);
//
//        Entry foundEntry = entryRepository.findById(entry1.getEntryId()).get();
//
//        assertThat(foundEntry).isEqualTo(entry1);
//    }
//
//    @Test
//    public void test_update_enry_by_id() {
//        Entry entry1 = new Entry(1, "Jack", "+79123456789");
//        entityManager.persist(entry1);
//
//        Entry entry2 = new Entry(1, "Jack", "+79321654987");
//
//        Entry entry = entryRepository.findById(entry1.getEntryId()).get();
//        entry.setName(entry2.getName());
//        entry.setName(entry2.getNumber());
//        entryRepository.save(entry);
//
//        Entry checkEntry = entryRepository.findById(entry1.getEntryId()).get();
//
//        assertThat(checkEntry.getEntryId()).isEqualTo(entry1.getEntryId());
//        assertThat(checkEntry.getUserId()).isEqualTo(entry1.getUserId());
//        assertThat(checkEntry.getName()).isEqualTo(entry1.getName());
//        assertThat(checkEntry.getNumber()).isEqualTo(entry1.getNumber());
//    }
}
