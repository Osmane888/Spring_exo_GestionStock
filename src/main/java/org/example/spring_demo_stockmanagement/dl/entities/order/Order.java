package org.example.spring_demo_stockmanagement.dl.entities.order;

import jakarta.persistence.*;
import lombok.*;
import org.example.spring_demo_stockmanagement.dl.entities.BaseEntity;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "order_")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@NoArgsConstructor
@Getter @Setter
@EqualsAndHashCode(callSuper = true) @ToString(callSuper = true)
public class Order extends BaseEntity {

    @Column(nullable = false)
    private LocalDate orderDate;

    @Column
    private String comment;

    public Order(UUID id, LocalDate orderDate, String comment) {
        super(id);
        this.orderDate = orderDate;
        this.comment = comment;
    }
}
