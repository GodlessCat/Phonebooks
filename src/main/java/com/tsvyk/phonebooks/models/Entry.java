package com.tsvyk.phonebooks.models;

import lombok.*;

import javax.persistence.*;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ENTRIES")
public class Entry {

    @Id
    @SequenceGenerator(name = "ENTRY_SEQUENCE", sequenceName = "ENTRY_SEQUENCE", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ENTRY_SEQUENCE")
    @Column(name = "ID")
    private long id;

    @NonNull
    @Column(name = "USER_ID")
    private long userId;

    @NonNull
    @Column(name = "ENTRY_NAME", length = 100, nullable = false)
    private String name;

    @NonNull
    @Column(name = "ENTRY_NUMBER", length = 11)
    private String number;
}