package org.example.posspring.service.impl;


import org.example.posspring.dao.CustomerDao;
import org.example.posspring.dao.ItemDao;
import org.example.posspring.dao.OrderDao;
import org.example.posspring.dao.OrderDetailsDao;
import org.example.posspring.dto.impl.OrderDTO;
import org.example.posspring.dto.impl.OrderDetailsDTO;
import org.example.posspring.entity.impl.*;
import org.example.posspring.exception.CustomerNotFoundException;
import org.example.posspring.exception.ItemNotFoundException;
import org.example.posspring.exception.ItemOutOfStockException;
import org.example.posspring.exception.OrderNotFoundException;
import org.example.posspring.service.OrderService;
import org.example.posspring.util.AppUtil;
import org.example.posspring.util.Mapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private Mapping mapper;
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private ItemDao itemDao;
    @Autowired
    private OrderDetailsDao orderDetailsDao;
    static Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);


    @Override
    public void addOrder(OrderDTO orderDto) {
        String orderId = AppUtil.generateOrderId();

        Order order = mapper.mapToOrder(orderDto);
        order.setId(orderId);

        Customer orderPlacingCustomer = customerDao.getReferenceById(orderDto.getCustomer_id());
        if (orderPlacingCustomer == null) {
            logger.error("Customer not in database , Customer not found exception occurred");
            throw new CustomerNotFoundException("Customer not found");
        }
        order.setCustomer(orderPlacingCustomer);

        List<OrderDetails> orderDetailsList = new ArrayList<>();
        order.setOrderDetails(orderDetailsList);

        orderDao.save(order);

        orderDto.getOrderDetails().forEach(orderDetailsDto -> {
            OrderItemId orderItemId = new OrderItemId(orderId, orderDetailsDto.getItem_id());
            Item orderDetails = itemDao.getReferenceById(orderDetailsDto.getItem_id());
            if (orderDetails == null) {
                logger.error("Item not in database , Item not found exception occurred");
                throw new ItemNotFoundException("Item not found");
            } else if (orderDetails.getQuantity() == 0) {
                logger.error("Item out of stock , Item out of stock exception occurred (Stock Remaining :"+orderDetails.getQuantity()+")");
                throw new ItemOutOfStockException("Item out of stock");
            }

            itemDao.save(orderDetails);

            OrderDetails orderDetailsToAdd = OrderDetails.builder()
                    .id(orderItemId)
                    .item(orderDetails)
                    .qty(orderDetailsDto.getQty())
                    .total(orderDetailsDto.getTotal())
                    .unit_price(orderDetailsDto.getUnit_price())
                    .order(order)
                    .build();
            orderDetailsList.add(orderDetailsToAdd);
            orderDetailsDao.save(orderDetailsToAdd);
        });
        order.setOrderDetails(orderDetailsList);
        orderDao.save(order);
    }

    @Override
    public List<OrderDTO> getAllOrders() {
        List<Order> orders = orderDao.findAll();
        List<OrderDTO> orderDtos = new ArrayList<>();

        orders.forEach(order -> {
            List<OrderDetailsDTO> orderDetailsDTOSList = getOrderDetails(order.getOrderDetails());
            OrderDTO orderDto = mapper.mapToOrderDto(order, orderDetailsDTOSList);
            orderDtos.add(orderDto);
        });
        return orderDtos;
    }

    @Override
    public OrderDTO getOrder(String orderId) {
        Order order = orderDao.getReferenceById(orderId);
        if (order == null) {
            throw new OrderNotFoundException("Order not found");
        }
        List<OrderDetailsDTO> orderItemList = getOrderDetails(order.getOrderDetails());
        return mapper.mapToOrderDto(order, orderItemList);
    }


    private List<OrderDetailsDTO> getOrderDetails(List<OrderDetails> orderDetails) {
        List<OrderDetailsDTO> orderItemDtos = new ArrayList<>();
        orderDetails.forEach(orderItem -> {
            OrderDetailsDTO orderDetailsDto = mapper.mapToOrderDetailsDto(orderItem);
            orderItemDtos.add(orderDetailsDto);
        });
        return orderItemDtos;
    }


}
