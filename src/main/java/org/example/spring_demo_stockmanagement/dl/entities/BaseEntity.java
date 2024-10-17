package org.example.spring_demo_stockmanagement.dl.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(of={"id"})
@ToString
@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @Setter
    private UUID id;

    @CreationTimestamp
    private LocalDate createdAt;
    @UpdateTimestamp
    private LocalDate updatedAt;

    public BaseEntity(UUID id){
        this.id = id;
    }
}
