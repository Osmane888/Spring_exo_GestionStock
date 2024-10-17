package org.example.spring_demo_stockmanagement.dl.entities.stock;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;
import org.example.spring_demo_stockmanagement.dl.entities.BaseEntity;
import org.example.spring_demo_stockmanagement.dl.entities.enums.StockMovementType;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter @Setter
@EqualsAndHashCode(callSuper = true) @ToString(callSuper = true)
public class StockMovement extends BaseEntity {

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StockMovementType movementType;

    @Column(nullable = false)
    @Min(0)
    private int quantity;

    @Column(nullable = false)
    private LocalDateTime movementDate;

    @ManyToOne(fetch = FetchType.EAGER)
    private Article article;

    public StockMovement(UUID id, StockMovementType movementType, int quantity, LocalDateTime movementDate, Article article) {
        super(id);
        this.movementType = movementType;
        this.quantity = quantity;
        this.movementDate = movementDate;
        this.article = article;
    }
}
