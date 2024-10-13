package org.example.posspring.dto.impl;


import lombok.*;
import org.example.posspring.dto.CustomerStatus;
import org.example.posspring.dto.SuperDTO;
import org.example.posspring.entity.impl.Order;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerDTO implements CustomerStatus, SuperDTO {

    private String  customer_id;
    private String name;
    private String address;
    private int phone;
    private List<Order> orders;
}
