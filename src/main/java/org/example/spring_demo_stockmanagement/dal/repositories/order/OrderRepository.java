package org.example.spring_demo_stockmanagement.dal.repositories.order;

import org.example.spring_demo_stockmanagement.dl.entities.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {
}
