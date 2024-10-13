package org.example.posspring.service;


import org.example.posspring.dto.CustomerStatus;
import org.example.posspring.dto.impl.CustomerDTO;

import java.util.List;

public interface CustomerService {
    void addCustomer(CustomerDTO customerDto);

}
