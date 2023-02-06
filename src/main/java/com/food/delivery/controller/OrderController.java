package com.food.delivery.controller;

import com.food.delivery.dto.CustomerOrderDto;
import com.food.delivery.entity.Customer;
import com.food.delivery.exception.EntityNotFountException;
import com.food.delivery.service.ICustomerService;
import com.food.delivery.service.IOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/customer")
@Slf4j
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createOrder(@RequestBody CustomerOrderDto orderDto) {
        log.info(" Received customer order request with mobile no: "+ orderDto.getPhone());
        return new ResponseEntity<>(orderService.createOrder(orderDto), HttpStatus.CREATED);
    }

}
