package org.example.posspring.dao;

import org.example.posspring.entity.impl.OrderDetails;
import org.example.posspring.entity.impl.OrderItemId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailsDao extends JpaRepository<OrderDetails, OrderItemId> {
}
