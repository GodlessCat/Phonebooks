package com.tsvyk.phonebooks;

import static org.assertj.core.api.Assertions.assertThat;

import com.tsvyk.phonebooks.models.Entry;
import com.tsvyk.phonebooks.models.User;
import com.tsvyk.phonebooks.repositories.EntryRepository;
import com.tsvyk.phonebooks.repositories.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class EntryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    EntryRepository entryRepository;

    @Test
    public void test_showing_no_content_if_no_entries_was_creating() {
        Iterable<Entry> entries = entryRepository.findAll();

        assertThat(entries).isEmpty();
    }

    @Test
    public void test_save_a_new_entry() {
        User user1 = userRepository.save(new User("John"));

        Entry entry = entryRepository.save(new Entry(user1.getUserId(), "Jack", "+79123456789"));

        assertThat(entry).hasFieldOrPropertyWithValue("name", "Jack");
        assertThat(entry).hasFieldOrPropertyWithValue("number", "+79123456789");
        assertThat(entry).hasFieldOrPropertyWithValue("userId", user1.getUserId());

    }

    @Test
    public void test_showing_entry_by_id() {
        Entry entry1 = new Entry(1, "Jack", "+79123456789");
        entityManager.persist(entry1);

        Entry foundEntry = entryRepository.findById(entry1.getEntryId()).get();

        assertThat(foundEntry).isEqualTo(entry1);
    }

    @Test
    public void test_update_enry_by_id() {
        Entry entry1 = new Entry(1, "Jack", "+79123456789");
        entityManager.persist(entry1);

        Entry entry2 = new Entry(1, "Jack", "+79321654987");

        Entry entry = entryRepository.findById(entry1.getEntryId()).get();
        entry.setName(entry2.getName());
        entry.setName(entry2.getNumber());
        entryRepository.save(entry);

        Entry checkEntry = entryRepository.findById(entry1.getEntryId()).get();

        assertThat(checkEntry.getEntryId()).isEqualTo(entry1.getEntryId());
        assertThat(checkEntry.getUserId()).isEqualTo(entry1.getUserId());
        assertThat(checkEntry.getName()).isEqualTo(entry1.getName());
        assertThat(checkEntry.getNumber()).isEqualTo(entry1.getNumber());
    }
}
