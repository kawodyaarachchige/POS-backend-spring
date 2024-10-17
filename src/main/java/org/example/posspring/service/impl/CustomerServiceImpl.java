package org.example.posspring.service.impl;


import org.example.posspring.controller.CustomerController;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    static Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Override
    public void addCustomer(CustomerDTO customerDto) {
        customerDto.setCustomer_id(AppUtil.generateCustomerId());
        Customer savedNote = customerDao.save(mapper.mapToCustomer(customerDto));
        if (savedNote == null) {
            logger.error("Failed to add customer , Data Persist Exception occurred");
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
            logger.error("Customer not in database , Customer not found exception occurred (Searching failed to retrieve :"+customer_id+")");
            return new SelectedCustomerStatus(2, "Customer not found");
        }
        return mapper.mapToCustomerDto(fetchedCustomer);
    }

    @Override
    public void deleteCustomer(String customer_id) {
        Customer customer = customerDao.getReferenceById(customer_id);
        if (customer == null) {
            logger.error("Customer not in database , Customer not found exception occurred (Searching failed to retrieve :"+customer_id+")");
            throw new CustomerNotFoundException("Customer not found");
        }
        customerDao.delete(customer);
    }

    @Override
    public void updateCustomer(String customer_id, CustomerDTO customerDto) {
        try {
            Customer customer = customerDao.getReferenceById(customer_id);
            if (customer == null) {
                logger.error("Customer not in database , Customer not found exception occurred (Searching failed to retrieve :"+customer_id+")");
                throw new CustomerNotFoundException("Customer not found");
            }
            customer.setName(customerDto.getName());
            customer.setAddress(customerDto.getAddress());
            customer.setPhone(customerDto.getPhone());
            customerDao.save(customer);
        } catch (DataPersistException e) {
            logger.error("Failed to update customer , Data Persist Exception occurred");
            throw new DataPersistException("Failed to update customer");
        }
    }

}
