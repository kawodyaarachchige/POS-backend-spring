package org.example.posspring.controller;

import jakarta.persistence.EntityNotFoundException;

import org.example.posspring.customstatuscode.SelectedOrderStatus;
import org.example.posspring.dto.OrderStatus;
import org.example.posspring.dto.impl.OrderDTO;
import org.example.posspring.exception.CustomerNotFoundException;
import org.example.posspring.exception.ItemNotFoundException;
import org.example.posspring.exception.ItemOutOfStockException;
import org.example.posspring.exception.OrderNotFoundException;
import org.example.posspring.service.OrderService;
import org.example.posspring.util.Regex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addOrder(@RequestBody OrderDTO orderDto) {
        try{
            orderService.addOrder(orderDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (CustomerNotFoundException | ItemNotFoundException | ItemOutOfStockException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public List<OrderDTO> getAllOrders(){
        return orderService.getAllOrders();
    }

    @GetMapping(value = "/{orderId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public OrderStatus getOrder(@PathVariable("orderId") String id) {
        boolean isOrderIdValid = Regex.ORDER_ID.validate(id);
        try{
            if(isOrderIdValid){
                return orderService.getOrder(id);
            }else{
                return new SelectedOrderStatus(1, "Order Id Invalid");
            }
        }catch (EntityNotFoundException | OrderNotFoundException e){
            e.printStackTrace();
            return new SelectedOrderStatus(2,"Order Not Found");
        }
    }

}
