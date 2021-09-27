package com.tsvyk.phonebooks.dto.user;

import com.tsvyk.phonebooks.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class UserNameNumber {

    private String name;

    private String number;

    public static UserNameNumber from(User user) {
        return UserNameNumber.builder()
                .name(user.getName())
                .number(user.getNumber())
                .build();
    }
}