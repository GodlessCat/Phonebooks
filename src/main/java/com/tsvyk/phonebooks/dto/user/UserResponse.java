package com.tsvyk.phonebooks.dto.user;

import com.tsvyk.phonebooks.models.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponse {

    private long id;

    private String name;

    public static UserResponse from(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .build();
    }
}
