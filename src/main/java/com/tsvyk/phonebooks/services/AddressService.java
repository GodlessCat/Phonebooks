package com.tsvyk.phonebooks.services;

import com.tsvyk.phonebooks.dto.address.AddressResponse;
import com.tsvyk.phonebooks.dto.address.AddressStreetNumber;
import com.tsvyk.phonebooks.exceptions.NoContentException;
import com.tsvyk.phonebooks.exceptions.NotFoundException;

import java.util.List;

public interface AddressService {
    List<AddressResponse> getAllAddresses() throws NotFoundException, NoContentException;

    AddressResponse getAddressById(long id) throws NoContentException, NotFoundException;

    AddressStreetNumber createAddress(AddressStreetNumber addressStreetNumber);

    AddressResponse updateAddress(long id, AddressStreetNumber addressStreetNumber) throws NotFoundException;

    void deleteAddressById(long id);
}
