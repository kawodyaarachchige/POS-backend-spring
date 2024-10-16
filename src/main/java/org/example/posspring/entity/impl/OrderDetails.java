package org.example.posspring.entity.impl;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.posspring.entity.SuperEntity;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "order_details")
@Builder
public class OrderDetails implements SuperEntity {
    @EmbeddedId
    private OrderItemId id;

    @MapsId("orderId")
    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @MapsId("itemId")
    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    private int qty;
    private double unit_price;
    private double total;
}
