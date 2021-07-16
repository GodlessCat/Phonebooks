package com.tsvyk.phonebooks.dto.user;

import com.tsvyk.phonebooks.dto.address.AddressStreetNumber;
import lombok.Data;

import java.util.Set;


@Data
public class UserRequest {

    private String street;

    private String number;

    private Set<AddressStreetNumber> streetNumbers;

}