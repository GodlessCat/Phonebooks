package com.tsvyk.phonebooks;

import com.tsvyk.phonebooks.dto.address.AddressResponse;
import com.tsvyk.phonebooks.dto.address.AddressStreetNumber;
import com.tsvyk.phonebooks.exceptions.NoContentException;
import com.tsvyk.phonebooks.exceptions.NotFoundException;
import com.tsvyk.phonebooks.models.Address;
import com.tsvyk.phonebooks.models.User;
import com.tsvyk.phonebooks.repositories.AddressRepository;
import com.tsvyk.phonebooks.services.impl.AddressServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class AddressTest {

    @MockBean
    private AddressRepository addressRepository;

    @Autowired
    private AddressServiceImpl addressService;

    @Test
    @DisplayName("Test find all address")
    void testFindAll() throws NoContentException {

        Address address1 = new Address("Lenina", 1);
        Address address2 = new Address("Lenina", 2);

        when(addressRepository.findAll()).thenReturn(Arrays.asList(address1, address2));

        List<AddressResponse> addresses = addressService.getAllAddresses();

        assertEquals(2, addresses.size());

        assertEquals("Lenina", addresses.get(0).getStreet());
        assertEquals(1, addresses.get(0).getNumber());

        assertEquals("Lenina", addresses.get(1).getStreet());
        assertEquals(2, addresses.get(1).getNumber());

        verify(addressRepository, times(1)).findAll();
    }


    @Test
    @DisplayName("Test find address by id")
    void testFindById() throws NotFoundException {

        when(addressRepository.findById(0L)).thenReturn(java.util.Optional.of(new Address("Lenina", 1)));

        AddressResponse addressById = addressService.getAddressById(0L);

        assertEquals("Lenina", addressById.getStreet());
        assertEquals(1, addressById.getNumber());
        verify(addressRepository, times(2)).findById(0L);
    }

    @Test
    @DisplayName("Test create address")
    public void testCreateAddress() throws NotFoundException {

        AddressStreetNumber addressStreetNumber = new AddressStreetNumber("Lenina", 1);

        when(addressRepository.save(any())).thenReturn(new Address("Lenina", 1));

        AddressStreetNumber address = addressService.createAddress(addressStreetNumber);

        assertEquals("Lenina", address.getStreet());
        assertEquals(1, address.getNumber());
        verify(addressRepository, times(1)).save(any());
    }

    @Test
    @DisplayName("Test update user")
    public void testUpdateUser() throws NotFoundException {

        AddressStreetNumber addressStreetNumber = new AddressStreetNumber("Lenina", 1);


        when(addressRepository.findById(0L)).thenReturn(java.util.Optional.of(new Address("Lenina", 1)));
        when(addressRepository.save(any())).thenReturn(new Address("Lenina", 1));

        AddressResponse updated = addressService.updateAddress(0L, addressStreetNumber);

        assertEquals("Lenina", updated.getStreet());
        assertEquals(1, updated.getNumber());

        verify(addressRepository, times(1)).findById(0L);
        verify(addressRepository, times(1)).save(any());
    }

    @Test
    @DisplayName("Test delete user")
    public void testDeleteUser() {

        addressService.deleteAddressById(0L);

        verify(addressRepository, times(1)).deleteById(0L);
    }

}