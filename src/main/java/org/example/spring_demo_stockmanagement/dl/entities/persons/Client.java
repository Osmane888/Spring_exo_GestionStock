package org.example.spring_demo_stockmanagement.dl.entities.persons;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;
import org.example.spring_demo_stockmanagement.dl.entities.Address;

import java.util.UUID;

@Entity
@DiscriminatorValue("client")
@NoArgsConstructor
@Getter @Setter
@EqualsAndHashCode(callSuper = true) @ToString(callSuper = true)
public class Client extends Extern{

    public Client(UUID id, String firstName, String lastName, String email, String phoneNumber, Address address) {
        super(id, firstName, lastName, email, phoneNumber, address);
    }
}