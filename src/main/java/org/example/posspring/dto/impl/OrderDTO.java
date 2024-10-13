package org.example.posspring.dto.impl;


import lombok.*;
import org.example.posspring.dto.OrderStatus;
import org.example.posspring.dto.SuperDTO;

import java.util.Date;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDTO implements OrderStatus, SuperDTO {
    private String id;
    private String customer_id;
    private Date date;
    private double discount_value;
    private double sub_total;
    List<OrderDetailsDTO> orderDetails;
}
