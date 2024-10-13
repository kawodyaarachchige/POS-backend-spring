package org.example.posspring.dao;


import org.example.posspring.entity.impl.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerDao extends JpaRepository<Customer, String> {
}
