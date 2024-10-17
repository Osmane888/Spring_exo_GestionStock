package org.example.spring_demo_stockmanagement.dl.entities.persons;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.*;
import org.example.spring_demo_stockmanagement.dl.entities.BaseEntity;

import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true) @ToString(callSuper = true)
public class Role extends BaseEntity {

    @Setter
    @Column(nullable = false, unique = true, length = 20)
    private String name;

    public Role(UUID id, String name) {
        super(id);
        this.name = name;
    }
}
