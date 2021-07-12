package com.tsvyk.phonebooks.utils;

import com.tsvyk.phonebooks.dto.entry.EntryResponse;
import com.tsvyk.phonebooks.dto.user.UserResponse;
import com.tsvyk.phonebooks.models.Entry;
import com.tsvyk.phonebooks.models.User;
import org.springframework.stereotype.Service;

@Service
public class MappingUtils {

    public EntryResponse mapToEntryResponse(Entry entry) {

        EntryResponse entryResponse = new EntryResponse();

        entryResponse.setEntryId(entry.getEntryId());
        entryResponse.setName(entry.getName());
        entryResponse.setNumber(entry.getNumber());

        return entryResponse;
    }


    public UserResponse mapToUserResponse(User user) {

        UserResponse userResponse = new UserResponse();

        userResponse.setUserId(user.getUserId());
        userResponse.setName(user.getName());

        return userResponse;
    }
}