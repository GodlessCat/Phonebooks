package com.tsvyk.phonebooks.dto.entry;

import lombok.Data;

@Data
public class EntryResponse {

    private long entryId;

    private String name;

    private String number;
}
