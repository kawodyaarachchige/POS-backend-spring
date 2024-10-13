package org.example.posspring.service.impl;


import org.example.posspring.dao.CustomerDao;
import org.example.posspring.dto.CustomerStatus;
import org.example.posspring.dto.impl.CustomerDTO;
import org.example.posspring.entity.impl.Customer;

import org.example.posspring.exception.DataPersistException;
import org.example.posspring.service.CustomerService;
import org.example.posspring.util.AppUtil;
import org.example.posspring.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private Mapping mapper;

    @Override
    public void addCustomer(CustomerDTO customerDto){
        customerDto.setCustomer_id(AppUtil.generateCustomerId());
        Customer savedNote = customerDao.save(mapper.mapToCustomer(customerDto));
        if(savedNote == null){
            throw new DataPersistException("Failed to add customer");
        }
    }

}
