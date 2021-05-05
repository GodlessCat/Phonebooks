package com.tsvyk.phonebooks.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Entry")
public class Entry {

    @Id
    @SequenceGenerator(name = "jpaSequence", sequenceName = "ENTRY_SEQUENCE", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "jpaSequence")
    long entryId;

    @NonNull
    @JsonIgnore
    @Column(name = "USER_ID")
    long userId;

    @NonNull
    @Column(name = "ENTRY_NAME")
    String name;

    @NonNull
    @Column(name = "ENTRY_NUMBER", length = 11)
    String number;
}