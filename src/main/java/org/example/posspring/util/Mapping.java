package org.example.posspring.util;


import org.example.posspring.dto.impl.CustomerDTO;
import org.example.posspring.dto.impl.ItemDTO;
import org.example.posspring.entity.impl.Customer;
import org.example.posspring.entity.impl.Item;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

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


}
