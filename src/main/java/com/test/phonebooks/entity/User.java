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
@Table(name = "USER")
public class User {

    @Id
    @SequenceGenerator( name = "jpaSequence", sequenceName = "USER_SEQUENCE", allocationSize = 1, initialValue = 1 )
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "jpaSequence")
    private long id;

    @NonNull
    @Column(name = "USER_NAME", length = 100, nullable = false)
    private String name;
}