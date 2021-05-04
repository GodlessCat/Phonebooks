package com.test.phonebooks.entity;


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
    @SequenceGenerator( name = "jpaSequence", sequenceName = "ENTRY_SEQUENCE", allocationSize = 1, initialValue = 1 )
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "jpaSequence")
    long id;

    @NonNull
    @Column(name = "ENTRY_OWNER_ID")
    long ownerID;

    @NonNull
    @Column(name = "ENTRY_NAME")
    String name;

    @NonNull
    @Column(name = "ENTRY_NUMBER", length = 11)
    String number;
}