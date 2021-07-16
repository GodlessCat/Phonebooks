package com.tsvyk.phonebooks.dto.address;

import com.tsvyk.phonebooks.dto.user.UserNameNumber;
import lombok.Data;

import java.util.Set;

@Data
public class AddressRequest {

    private String street;

    private int number;

    private Set<UserNameNumber> users;
}