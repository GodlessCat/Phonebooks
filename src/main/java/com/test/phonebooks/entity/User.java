package com.test.phonebooks.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "USER")
public class User {

    @Id
    @SequenceGenerator( name = "jpaSequence", sequenceName = "USER_SEQUENCE", allocationSize = 1, initialValue = 1 )
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "jpaSequence")
    private long user_id;

    @NonNull
    @Column(name = "USER_NAME", length = 100, nullable = false)
    private String name;

    @OneToMany(
            mappedBy = "entry_id",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )

    @JsonIgnore
    private List<Entry> entries = new ArrayList<>();
}