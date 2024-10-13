package org.example.posspring.dao;


import org.example.posspring.entity.impl.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDao extends JpaRepository<Order,String> {
}
