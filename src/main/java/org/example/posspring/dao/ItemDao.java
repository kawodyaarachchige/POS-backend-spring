package org.example.posspring.dao;

import org.example.posspring.entity.impl.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemDao extends JpaRepository<Item,String> {
}
