package com.tsvyk.phonebooks.repositories;

import com.tsvyk.phonebooks.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByNameContaining(String name);

    User findByUserId(long id);

}
