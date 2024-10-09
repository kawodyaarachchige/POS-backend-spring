package org.example.posspring.entity.impl;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.posspring.entity.SuperEntity;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "item")
@Builder
public class Item implements SuperEntity {
    @Id
    private String  item_id;
    private String description;
    private double price;
    private int quantity;
    @OneToMany(mappedBy = "item",cascade = CascadeType.ALL,fetch = FetchType.EAGER,orphanRemoval = true)
    private List<OrderDetails> orderDetails;
}
