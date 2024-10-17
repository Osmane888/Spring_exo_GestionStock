package org.example.spring_demo_stockmanagement.dl.entities.order;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.example.spring_demo_stockmanagement.dl.entities.persons.Client;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true) @ToString(callSuper = true)
public class ClientOrder extends Order {

    @ManyToOne(fetch = FetchType.EAGER)
    private Client client;

    public ClientOrder(UUID id, LocalDate orderDate, String comment) {
        super(id, orderDate, comment);
    }
}