package org.example.posspring.dto.impl;



import lombok.*;
import org.example.posspring.dto.OrderStatus;
import org.example.posspring.dto.SuperDTO;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDetailsDTO implements SuperDTO, OrderStatus {
    private String order_id;
    private String item_id;
    private int  qty;
    private double unit_price;
    private double total;
}
