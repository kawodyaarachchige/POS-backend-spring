package org.example.posspring.entity.impl;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.posspring.entity.SuperEntity;

import java.util.Date;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "orders")
@Builder
public class Order implements SuperEntity {
    @Id
    private String id;
    private Date date;
    private double discount_value;
    private double sub_total;
    @ManyToOne
    @JoinColumn(name = "customer_id",nullable = false)
    private Customer customer;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.EAGER)
    private List <OrderDetails> orderDetails;
}
