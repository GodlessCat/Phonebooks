package com.tsvyk.phonebooks;

import static org.assertj.core.api.Assertions.assertThat;

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
public class UserTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    EntryRepository entryRepository;

    @Test
    public void test_showing_no_content_if_no_users_was_creating() {
        Iterable<User> users = userRepository.findAll();

        assertThat(users).isEmpty();
    }

    @Test
    public void test_save_a_new_user() {
        User user = userRepository.save(new User("John"));

        assertThat(user).hasFieldOrPropertyWithValue("name", "John");
    }

    @Test
    public void test_showing_all_users() {
        User user1 = new User("John");
        entityManager.persist(user1);

        User user2 = new User("Jack");
        entityManager.persist(user2);

        Iterable<User> users = userRepository.findAll();

        assertThat(users).hasSize(2).contains(user1, user2);
    }

    @Test
    public void test_showing_user_by_id() {
        User user1 = new User("John");
        entityManager.persist(user1);

        User user2 = new User("Jack");
        entityManager.persist(user2);

        User foundUser = userRepository.findById(user2.getUserId()).get();

        assertThat(foundUser).isEqualTo(user2);
    }

    @Test
    public void test_showing_users_by_part_of_name() {
        User user1 = new User("John");
        entityManager.persist(user1);

        User user2 = new User("Jack");
        entityManager.persist(user2);

        User user3 = new User("Joe");
        entityManager.persist(user3);


        Iterable<User> users = userRepository.findByNameContaining("Jo");

        assertThat(users).hasSize(2).contains(user1, user3);
    }

    @Test
    public void test_update_user_by_id() {
        User user1 = new User("John");
        entityManager.persist(user1);

        User updatedUser = new User("Joe");

        User user = userRepository.findById(user1.getUserId()).get();
        user.setName(updatedUser.getName());
        userRepository.save(user);

        User checkUser = userRepository.findById(user1.getUserId()).get();

        assertThat(checkUser.getUserId()).isEqualTo(user1.getUserId());
        assertThat(checkUser.getName()).isEqualTo(user1.getName());
    }

    @Test
    public void test_delete_user_by_id() {
        User user1 = new User("John");
        entityManager.persist(user1);

        User user2 = new User("Jack");
        entityManager.persist(user2);

        userRepository.deleteById(user2.getUserId());

        Iterable<User> users = userRepository.findAll();

        assertThat(users).hasSize(1).contains(user1);
    }

//    @Test
//    public void test_showing_all_entries_by_user_id() {
//        User user1 = userRepository.save(new User("Jack"));
//        User user2 = userRepository.save(new User("John"));
//
//        Entry entry1 = entryRepository.save(new Entry(user1.getUserId(), "John", "+79123456789"));
//        Entry entry2 = entryRepository.save(new Entry(user2.getUserId(), "Joe", "+79987654321"));
//        Entry entry3 = entryRepository.save(new Entry(user1.getUserId(), "Bill", "+79654987321"));
//
//
//        Iterable<Entry> foundEntries = entryRepository.findByUserId((Long) entityManager.getId(user1));
//        assertThat(foundEntries).hasSize(2).contains(entry1, entry3);
//    }
}
