package org.example.spring_demo_stockmanagement.dl.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode @ToString
public class Address {

    @Column(nullable = false)
    private String street;
    @Column(nullable = false)
    private String city;
    @Column(nullable = true)
    private String municipality;
    @Column(nullable = false)
    private String zip;
}
