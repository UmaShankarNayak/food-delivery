package com.food.delivery.service;

import com.food.delivery.dto.CustomerOrderDto;
import com.food.delivery.entity.DeliveryBoy;
import com.food.delivery.entity.Order;

public interface IOrderService {

    String createOrder(CustomerOrderDto order);

    void assigningOrdersToDeliveryBoys(DeliveryBoy deliveryBoy, Order order);

    void updateOrderStatus(String phoneNo);
}
