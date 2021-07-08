package com.tsvyk.phonebooks.dto.entry;

import lombok.Data;

@Data
public class EntryResponse {

    long entryId;

    String name;

    String number;
}
