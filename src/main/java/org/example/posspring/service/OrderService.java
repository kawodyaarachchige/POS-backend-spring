package org.example.posspring.service;


import org.example.posspring.dto.OrderStatus;
import org.example.posspring.dto.impl.OrderDTO;


import java.util.List;

public interface OrderService {
    void addOrder(OrderDTO orderDto);

    List<OrderDTO> getAllOrders();

    OrderStatus getOrder(String orderId);

}
