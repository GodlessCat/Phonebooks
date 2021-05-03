package com.test.phonebooks.entity;


import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "Entry")
@Data
public class Entry {

    @Id
    @GeneratedValue
    @Column
    long id;

    @Column(name = "ENTRY_NAME")
    String name;

    @Column(name = "ENTRY_NUMBER", length = 11)
    String number;
}
