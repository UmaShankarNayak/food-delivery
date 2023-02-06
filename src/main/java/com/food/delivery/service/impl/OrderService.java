package com.food.delivery.service.impl;

import com.food.delivery.dto.CustomerOrderDto;
import com.food.delivery.entity.Address;
import com.food.delivery.entity.Customer;
import com.food.delivery.entity.DeliveryBoy;
import com.food.delivery.entity.Order;
import com.food.delivery.exception.EntityNotFountException;
import com.food.delivery.exception.BusinessException;
import com.food.delivery.repository.AddressRepository;
import com.food.delivery.repository.CustomerRepository;
import com.food.delivery.repository.DeliveryAgentRepository;
import com.food.delivery.repository.OrderRepository;
import com.food.delivery.service.IOrderService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
@Slf4j
public class OrderService implements IOrderService {

    private static Boolean DELIVERY_AGENT_AVAILABLE=false;
    private static Boolean NOT_DELIVERY_AGENT_AVAILABLE=true;

    @Autowired
    private DeliveryAgentRepository deliveryAgentRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public String createOrder(CustomerOrderDto cOrder) {
        //
        if(isDeliveryBoy()){
            throw new BusinessException("No Food Found, Customer try with phone: ", cOrder.getPhone());
        }
       // create order object.
        Optional<Customer> customer=customerRepository.findByPhoneNo(cOrder.getPhone());
        if(customer.isEmpty()){
            throw new EntityNotFountException(Customer.class, " Phone No. "+cOrder.getPhone());
        }
        Order order = orderRepository.save(prepareOrder(cOrder, customer.get()));

        return "Order Has been created successfully with order id "+order.getId();

    }

    @Override
    public void assigningOrdersToDeliveryBoys(DeliveryBoy deliveryBoy, Order order) {
        order.setDeliveryBoy(deliveryBoy);
        order.setStatus("In-Progress");
        deliveryBoy.setAvailableTime(order.getCompilationTime());
        deliveryBoy.setAvailable(NOT_DELIVERY_AGENT_AVAILABLE);
        deliveryAgentRepository.save(deliveryBoy);
        orderRepository.save(order);
        log.info("Order Has been placed to customer.");
    }

    @Override
    public void updateOrderStatus(String phoneNo) {
        orderRepository.updateStatus(phoneNo);
    }


    private boolean isDeliveryBoy(){
       List<DeliveryBoy> agents= deliveryAgentRepository.getByStatus(DELIVERY_AGENT_AVAILABLE);
        if(agents == null ||agents.isEmpty()){
            return true;
        }
        return false;
    }

    private Order prepareOrder(CustomerOrderDto dto, Customer customer){
        Order order= new Order();
        order.setCustomer(customer);
        order.setCreationDate(ZonedDateTime.now());
        order.setStatus("Created");
        order.setDescription(dto.getDescription());
        order.setPrice(dto.getPrice());
        order.setAddress(validateAddress(dto));
        order.setCompilationTime(ZonedDateTime.now().plusMinutes(dto.getCompilationTime()));
       return order;
    }

    private Address validateAddress(CustomerOrderDto dto){
        Address address =addressRepository.findByCustomerPhoneAndPostalCode(dto.getPhone(), dto.getPostalCode());
        if(address == null){
            throw new EntityNotFountException(Address.class, " Location is "+dto.getPostalCode());
        }return address;
    }
}
