package org.example.posspring.dto.impl;

import lombok.*;
import org.example.posspring.dto.ItemStatus;
import org.example.posspring.dto.SuperDTO;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemDTO implements SuperDTO, ItemStatus {
    private String item_id;
    private String name;
    private String description;
    private double price;
    private int quantity;
}