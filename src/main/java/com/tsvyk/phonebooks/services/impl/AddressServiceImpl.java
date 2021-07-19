package com.tsvyk.phonebooks.services.impl;

import com.tsvyk.phonebooks.dto.address.AddressResponse;
import com.tsvyk.phonebooks.dto.address.AddressStreetNumber;
import com.tsvyk.phonebooks.exceptions.NoContentException;
import com.tsvyk.phonebooks.exceptions.NotFoundException;
import com.tsvyk.phonebooks.models.Address;
import com.tsvyk.phonebooks.repositories.AddressRepository;
import com.tsvyk.phonebooks.repositories.UserRepository;
import com.tsvyk.phonebooks.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    private final UserRepository userRepository;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository, UserRepository userRepository) {
        this.addressRepository = addressRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<AddressResponse> getAllAddresses() throws NoContentException {

        List<Address> addresses = addressRepository.findAll();

        if (addresses.isEmpty()) {
            throw new NoContentException();
        }

        List<AddressResponse> addressResponses = new ArrayList<>();

        for (Address address : addresses) {
            addressResponses.add(AddressResponse.from(address));
        }

        return addressResponses;
    }

    @Override
    public AddressResponse getAddressById(long id) throws NotFoundException {

        if (addressRepository.findById(id).isEmpty()) {
            throw new NotFoundException();
        }

        return AddressResponse.from(addressRepository.findById(id).get());
    }

    @Override
    public AddressStreetNumber createAddress(AddressStreetNumber addressStreetNumber) {

        Address address = new Address();
        address.setStreet(addressStreetNumber.getStreet());
        address.setNumber(addressStreetNumber.getNumber());
        address.setUsers(new HashSet<>());

        return AddressStreetNumber.from(addressRepository.save(address));
    }

    @Override
    public AddressResponse updateAddress(long id, AddressStreetNumber addressStreetNumber) throws NotFoundException {

        Optional<Address> address = addressRepository.findById(id);

        if (address.isEmpty()) {
            throw new NotFoundException();
        }

        address.get().setStreet(addressStreetNumber.getStreet());
        address.get().setNumber(addressStreetNumber.getNumber());

        addressRepository.save(address.get());

        return AddressResponse.from(address.get());
    }

    @Override
    public void deleteAddressById(long id) {

        addressRepository.deleteById(id);
    }
}
