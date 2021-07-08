package com.tsvyk.phonebooks.dto.user;

import lombok.Data;
import lombok.NonNull;

@Data
public class UserRequest {

    @NonNull
    private String name;
}
