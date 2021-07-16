package com.tsvyk.phonebooks.controllers;

import com.tsvyk.phonebooks.dto.address.AddressRequest;
import com.tsvyk.phonebooks.dto.address.AddressResponse;
import com.tsvyk.phonebooks.dto.address.AddressStreetNumber;
import com.tsvyk.phonebooks.exceptions.NoContentException;
import com.tsvyk.phonebooks.exceptions.NotFoundException;
import com.tsvyk.phonebooks.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("addresses")
public class AddressController {

    private final AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping
    public ResponseEntity<List<AddressResponse>> getAllAddress() {

        try {
            List<AddressResponse> addresses = addressService.getAllAddresses();

            return new ResponseEntity<>(addresses, HttpStatus.OK);

        } catch (NoContentException e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressResponse> getAddressById(@PathVariable(name = "id") long id) {

        try {
            AddressResponse address = addressService.getAddressById(id);

            return new ResponseEntity<>(address, HttpStatus.OK);

        } catch (NoContentException e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/new")
    public ResponseEntity<AddressResponse> createAddress(@RequestBody AddressStreetNumber addressStreetNumber) {

        try {

            AddressResponse addressResponse = addressService.createAddress(addressStreetNumber);

            return new ResponseEntity<>(addressResponse, HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{addressId}")
    public ResponseEntity<AddressResponse> updateAddress(@PathVariable("addressId") long addressId,
                                                         @RequestBody AddressStreetNumber addressStreetNumber) {

        try {
            AddressResponse addressResponse = addressService.updateAddress(addressId, addressStreetNumber);

            return new ResponseEntity<>(addressResponse, HttpStatus.OK);

        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{addressId}")
    public ResponseEntity<HttpStatus> deleteAddressById(@PathVariable("addressId") long addressId) {

        try {
            addressService.deleteAddressById(addressId);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{addressId}/addUser/{userId}")
    public ResponseEntity<AddressResponse> addUserToAddress(@PathVariable("addressId") long addressId,
                                                            @PathVariable("userId") long userId) {

        try {
            AddressResponse addressResponse = addressService.addUserToAddress(addressId, userId);

            return new ResponseEntity<>(addressResponse, HttpStatus.OK);

        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{addressId}/deleteUser/{userId}")
    public ResponseEntity<AddressResponse> deleteUserFromAddress(@PathVariable("addressId") long addressId,
                                                            @PathVariable("userId") long userId) {

        try {
            AddressResponse addressResponse = addressService.deleteUserFromAddress(addressId, userId);

            return new ResponseEntity<>(addressResponse, HttpStatus.OK);

        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}