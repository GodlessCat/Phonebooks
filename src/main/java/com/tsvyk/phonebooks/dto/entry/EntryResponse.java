package com.tsvyk.phonebooks.dto.entry;

import com.tsvyk.phonebooks.models.Entry;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EntryResponse {

    private String name;

    private String number;

    public static EntryResponse from(Entry entry) {
        return EntryResponse.builder()
                .name(entry.getName())
                .number(entry.getNumber())
                .build();
    }
}
