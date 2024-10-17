package org.example.spring_demo_stockmanagement.dal.repositories.order;

import org.example.spring_demo_stockmanagement.dl.entities.order.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderLineRepository extends JpaRepository<OrderLine, OrderLine.OrderLineId> {
}
