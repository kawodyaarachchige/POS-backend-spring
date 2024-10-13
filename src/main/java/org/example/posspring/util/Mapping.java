package org.example.posspring.util;


import org.example.posspring.dto.impl.CustomerDTO;
import org.example.posspring.dto.impl.ItemDTO;
import org.example.posspring.dto.impl.OrderDTO;
import org.example.posspring.dto.impl.OrderDetailsDTO;
import org.example.posspring.entity.impl.Customer;
import org.example.posspring.entity.impl.Item;
import org.example.posspring.entity.impl.Order;
import org.example.posspring.entity.impl.OrderDetails;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.time.LocalDate;
@Component
public class Mapping {
    @Autowired
    private ModelMapper modelMapper;

    public CustomerDTO mapToCustomerDto(Customer customer){
        return modelMapper.map(customer, CustomerDTO.class);
    }
    public Customer mapToCustomer(CustomerDTO customerDto){
        return modelMapper.map(customerDto, Customer.class);
    }
    public List<CustomerDTO> mapToCustomerDtoList(List<Customer> customers){
        return modelMapper.map(customers, List.class);
    }

    public ItemDTO mapToItemDto(Item item){
        return modelMapper.map(item,ItemDTO.class);
    }
    public Item mapToItem(ItemDTO itemDto){
        return modelMapper.map(itemDto,Item.class);
    }
    public List<ItemDTO> mapToItemDtoList(List<Item> items){
        return modelMapper.map(items, List.class);
    }


    public Order mapToOrder(OrderDTO OrderDto){
        return modelMapper.map(OrderDto, Order.class);
    }

    public OrderDTO mapToOrderDto(Order order, List<OrderDetailsDTO> orderDetailsDtos){
        return OrderDTO.builder()
                .id(order.getId())
                .customer_id(order.getCustomer().getCustomer_id())
                .date(order.getDate())
                .discount_value(order.getDiscount_value())
                .sub_total(order.getSub_total())
                .orderDetails(orderDetailsDtos)
                .build();
    }

    public OrderDetailsDTO mapToOrderDetailsDto(OrderDetails orderDetails){
        return OrderDetailsDTO.builder()
                .order_id(orderDetails.getId().getOrderId())
                .item_id(orderDetails.getId().getItemId())
                .qty(orderDetails.getQty())
                .unit_price(orderDetails.getUnit_price())
                .total(orderDetails.getTotal())
                .build();
    }
}
