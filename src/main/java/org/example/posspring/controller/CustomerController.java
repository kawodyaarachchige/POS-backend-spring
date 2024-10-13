package org.example.posspring.controller;


import org.example.posspring.customstatuscode.SelectedCustomerStatus;
import org.example.posspring.dto.CustomerStatus;
import org.example.posspring.dto.impl.CustomerDTO;
import org.example.posspring.exception.CustomerNotFoundException;
import org.example.posspring.exception.DataPersistException;
import org.example.posspring.service.CustomerService;

import org.example.posspring.util.Regex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addCustomer(@RequestBody CustomerDTO customerDto) {
        try {
            customerService.addCustomer(customerDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DataPersistException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CustomerDTO> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping(value = "/{customerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CustomerStatus getCustomer(@PathVariable("customerId") String customer_id) {
        boolean isCustomerIdValid = Regex.CUSTOMER_ID.validate(customer_id);
        if (isCustomerIdValid) {
            return customerService.getCustomer(customer_id);
        }else{
            return new SelectedCustomerStatus(1, "Customer Id Invalid");
        }
    }
    @DeleteMapping(value = "/{customerId}")
            public ResponseEntity<Void> deleteCustomer(@PathVariable("customerId") String customer_id) {
        boolean isCustomerIdValid = Regex.CUSTOMER_ID.validate(customer_id);
        try {
            if(isCustomerIdValid){
                customerService.deleteCustomer(customer_id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }else{
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }catch (CustomerNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}
