package org.example.spring_demo_stockmanagement.dl.entities.persons;

import jakarta.persistence.*;
import lombok.*;
import org.example.spring_demo_stockmanagement.dl.entities.Address;

import java.util.UUID;

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "extern_type")
@Entity
@NoArgsConstructor
@Getter @Setter
@EqualsAndHashCode(callSuper = true) @ToString(callSuper = true)
public abstract class Extern extends Person{

    @Column(nullable = false)
    private String phoneNumber;
    @Embedded
    private Address address;


    public Extern(UUID id, String firstName, String lastName, String email, String phoneNumber, Address address) {
        super(id, firstName, lastName, email);
        this.phoneNumber = phoneNumber;
        this.address = address;
    }
}
