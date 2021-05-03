package com.test.phonebooks.repository;

import com.test.phonebooks.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByNameContaining(String name);

    //List<PhoneNote> findByNumber(String number);
}
