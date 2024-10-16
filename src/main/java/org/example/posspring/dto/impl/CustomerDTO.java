package org.example.posspring.dto.impl;


import lombok.*;
import org.example.posspring.dto.CustomerStatus;
import org.example.posspring.dto.SuperDTO;


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
}
