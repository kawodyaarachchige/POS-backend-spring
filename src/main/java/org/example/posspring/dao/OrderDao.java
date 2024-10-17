package org.example.posspring.dao;


import org.example.posspring.entity.impl.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDao extends JpaRepository<Order,String> {
}
