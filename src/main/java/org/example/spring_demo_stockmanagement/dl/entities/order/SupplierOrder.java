package org.example.spring_demo_stockmanagement.dl.entities.order;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.example.spring_demo_stockmanagement.dl.entities.persons.Supplier;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true) @ToString(callSuper = true)
public class SupplierOrder extends  Order{

    @ManyToOne(fetch = FetchType.EAGER)
    private Supplier supplier;

    public SupplierOrder(UUID id, LocalDate orderDate, String comment, Supplier supplier) {
        super(id, orderDate, comment);
        this.supplier = supplier;
    }
}