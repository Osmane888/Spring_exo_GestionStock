package org.example.spring_demo_stockmanagement.dl.entities.persons;

import jakarta.persistence.*;
import lombok.*;
import org.example.spring_demo_stockmanagement.dl.entities.BaseEntity;

import java.util.UUID;

@MappedSuperclass
@Getter @Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true) @ToString(callSuper = true)
public abstract class Person extends BaseEntity {

    @Column(nullable = false, length = 123)
    private String firstName;
    @Column(nullable = false, length = 80)
    private String lastName;
    @Column(nullable = false, unique = true, length = 320)
    private String email;

    public Person(UUID id, String firstName, String lastName, String email) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
}
