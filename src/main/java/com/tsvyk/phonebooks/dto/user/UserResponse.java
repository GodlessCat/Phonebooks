package com.tsvyk.phonebooks.dto.user;

import com.tsvyk.phonebooks.dto.address.AddressStreetNumber;
import com.tsvyk.phonebooks.models.Address;
import com.tsvyk.phonebooks.models.User;
import lombok.Builder;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
public class UserResponse {

    private long id;

    private String name;

    private Set<AddressStreetNumber> addresses;

    public static UserResponse from(User user) {

        Set<AddressStreetNumber> addressStreetNumbers = new HashSet<>();

        for (Address address : user.getAddresses()){
            addressStreetNumbers.add(AddressStreetNumber.from(address));
        }

        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .addresses(addressStreetNumbers)
                .build();
    }
}
