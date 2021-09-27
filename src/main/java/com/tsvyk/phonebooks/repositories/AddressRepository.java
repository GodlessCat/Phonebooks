package com.tsvyk.phonebooks.repositories;

import com.tsvyk.phonebooks.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Long> {

    Optional<Address> findByStreetAndNumber(String street, int number);
}
