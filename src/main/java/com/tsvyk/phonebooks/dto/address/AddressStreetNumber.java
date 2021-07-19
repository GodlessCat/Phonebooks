package com.tsvyk.phonebooks.dto.address;

import com.tsvyk.phonebooks.models.Address;
import liquibase.pro.packaged.A;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class AddressStreetNumber {

    private String street;

    private int number;

    public static AddressStreetNumber from(Address address){
        return AddressStreetNumber.builder()
                .street(address.getStreet())
                .number(address.getNumber())
                .build();
    }
}
