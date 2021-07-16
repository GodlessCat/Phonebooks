package com.tsvyk.phonebooks.dto.address;

import com.tsvyk.phonebooks.dto.user.UserResponse;
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

    private String name;

    private Set<UserResponse> userResponses;

    public static AddressResponse from(Address address) {

        Set<UserResponse> userResponses = new HashSet<>();

        for (User user : address.getUsers()){
            userResponses.add(UserResponse.from(user));
        }

        return AddressResponse.builder()
                .name(address.getStreet())
                .number(address.getNumber())
                .userResponses(userResponses)
                .build();
    }
}
