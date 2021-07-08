package com.tsvyk.phonebooks.repositories;

import com.tsvyk.phonebooks.models.Entry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EntryRepository extends JpaRepository<Entry, Long> {

    List<Entry> findByNumber(String number);

    List<Entry> findByUserId(long id);

    List<Entry> findByEntryId(long id);

}

