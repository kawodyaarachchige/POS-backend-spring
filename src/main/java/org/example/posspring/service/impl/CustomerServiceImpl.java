package org.example.posspring.service.impl;


import org.example.posspring.customstatuscode.SelectedCustomerStatus;
import org.example.posspring.dao.CustomerDao;
import org.example.posspring.dto.CustomerStatus;
import org.example.posspring.dto.impl.CustomerDTO;
import org.example.posspring.entity.impl.Customer;

import org.example.posspring.exception.CustomerNotFoundException;
import org.example.posspring.exception.DataPersistException;
import org.example.posspring.service.CustomerService;
import org.example.posspring.util.AppUtil;
import org.example.posspring.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


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

    @Override
    public List<CustomerDTO> getAllCustomers() {
       return mapper.mapToCustomerDtoList(customerDao.findAll());
    }

    @Override
    public CustomerStatus getCustomer(String customer_id) {
        Customer fetchedCustomer = customerDao.getReferenceById(customer_id);
        if (fetchedCustomer == null) {
            return new SelectedCustomerStatus(2, "Customer not found");
        }
        return mapper.mapToCustomerDto(fetchedCustomer);
    }

    @Override
    public void deleteCustomer(String customer_id) {
        Customer customer = customerDao.getReferenceById(customer_id);
        if (customer == null) {
            throw new CustomerNotFoundException("Customer not found");
        }
        customerDao.delete(customer);
    }

}
