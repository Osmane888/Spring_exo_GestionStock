package org.example.spring_demo_stockmanagement.dl.entities.stock;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;
import org.example.spring_demo_stockmanagement.dl.entities.BaseEntity;

import java.util.UUID;

@Entity
@Getter @Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true) @ToString(callSuper = true)
public class Category extends BaseEntity {

    @Column(nullable = false, unique = true, length = 80)
    private String designation;

    public Category(UUID id, String designation) {
        super(id);
        this.designation = designation;
    }
}
