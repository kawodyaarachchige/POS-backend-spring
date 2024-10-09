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
@Table(name = "customer")
@Builder
public class Customer implements SuperEntity {
    @Id
    private String  customer_id;
    private String name;
    private String address;
    @Column(unique = true)
    private int phone;
    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER,cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Order> orders;

}
