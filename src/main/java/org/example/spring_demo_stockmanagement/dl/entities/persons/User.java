package org.example.spring_demo_stockmanagement.dl.entities.persons;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Table(name = "user_")
@Entity
@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true) @ToString(callSuper = true)
public class User extends Person{

    @Setter
    @Column(nullable = false)
    private String password;

    @ManyToMany
    private final Set<Role> roles = new HashSet<>();


    public User(UUID id, String firstName, String lastName, String email, String password) {
        super(id, firstName, lastName, email);
        this.password = password;
    }



    public Set<Role> getRoles(){
        return Set.copyOf(roles);
    }

    public void addRole(Role role){
        roles.add(role);
    }

    public void removeRole(Role role){
        roles.remove(role);
    }
}
