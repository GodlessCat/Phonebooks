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
@Table(name = "ENTRY")
public class Entry {

    @Id
    @SequenceGenerator(name = "ENTRY_SEQUENCE", sequenceName = "ENTRY_SEQUENCE", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ENTRY_SEQUENCE")
    @Column(name = "ID")
    private long id;

    @NonNull
    @JsonIgnore
    @Column(name = "USER_ID")
    private long userId;

    @NonNull
    @Column(name = "ENTRY_NAME", length = 100, nullable = false)
    private String name;

    @NonNull
    @Column(name = "ENTRY_NUMBER", length = 11)
    private String number;
}