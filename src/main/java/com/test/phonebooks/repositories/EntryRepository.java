package com.test.phonebooks.repositories;

import com.test.phonebooks.entity.Entry;
import com.test.phonebooks.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EntryRepository extends JpaRepository<Entry, Long> {

    List<Entry> findByNumber(String number);
    List<Entry> findByUser(Optional<User> user);
}

