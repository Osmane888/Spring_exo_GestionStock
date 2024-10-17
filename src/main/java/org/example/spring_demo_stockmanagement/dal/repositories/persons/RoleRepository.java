package org.example.spring_demo_stockmanagement.dal.repositories.persons;

import org.example.spring_demo_stockmanagement.dl.entities.persons.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {
}
