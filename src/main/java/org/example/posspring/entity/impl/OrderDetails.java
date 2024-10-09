package org.example.posspring.entity.impl;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.posspring.entity.SuperEntity;



@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "order_details")
public class OrderDetails  implements SuperEntity {
    @EmbeddedId
    private OderItemId id;
    @MapsId("order_id")
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
    @MapsId("item_id")
    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;
    private int qty;
    private double unit_price;
    private double total;

}
