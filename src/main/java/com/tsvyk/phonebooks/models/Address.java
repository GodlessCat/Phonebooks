package com.tsvyk.phonebooks.models;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ADDRESSES")
public class Address {

    @Id
    @SequenceGenerator(name = "ADDRESS_SEQUENCE", sequenceName = "ADDRESS_SEQUENCE", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ADDRESS_SEQUENCE")
    @Column(name = "ADDRESS_ID")
    private long id;

    @NonNull
    @Column(name = "ADDRESS_STREET", length = 100, nullable = false)
    private String street;

    @NonNull
    @Column(name = "ADDRESS_NUMBER", length = 100, nullable = false)
    private int number;

    @NonNull
    @ManyToMany(mappedBy = "addresses")
    private Set<User> users = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Address address = (Address) o;
        return Objects.equals(id, address.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}