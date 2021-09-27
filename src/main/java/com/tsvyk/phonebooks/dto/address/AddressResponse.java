package com.tsvyk.phonebooks.dto.address;

import com.tsvyk.phonebooks.dto.user.UserNameNumber;
import com.tsvyk.phonebooks.models.Address;
import com.tsvyk.phonebooks.models.User;
import lombok.Builder;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
public class AddressResponse {

    private int number;

    private String street;

    private Set<UserNameNumber> userNameNumbers;

    public static AddressResponse from(Address address) {

        Set<UserNameNumber> userNameNumbers = new HashSet<>();

        for (User user : address.getUsers()){
            userNameNumbers.add(UserNameNumber.from(user));
        }

        return AddressResponse.builder()
                .street(address.getStreet())
                .number(address.getNumber())
                .userNameNumbers(userNameNumbers)
                .build();
    }
}