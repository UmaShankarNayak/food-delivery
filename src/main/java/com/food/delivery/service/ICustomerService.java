package com.food.delivery.service;

import com.food.delivery.entity.Customer;

public interface ICustomerService {
    Customer getCustomer(String phoneNo);
}
