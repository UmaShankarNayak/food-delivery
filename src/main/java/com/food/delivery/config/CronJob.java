package com.food.delivery.config;

import com.food.delivery.entity.DeliveryBoy;
import com.food.delivery.entity.Order;
import com.food.delivery.repository.DeliveryAgentRepository;
import com.food.delivery.repository.OrderRepository;
import com.food.delivery.service.impl.OrderService;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@Component
@Getter
@Slf4j
public class CronJob {

    private Queue<DeliveryBoy> deliveryBoyQueue = new LinkedList<>();
    private Queue<Order> orderQueue = new LinkedList<>();

    private static final String ORDER_STATUS_CREATED="Created";
    private static final Boolean DELIVERY_BOY_AVAILABLE=false;

    @Autowired
    private DeliveryAgentRepository deliveryAgentRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderService orderService;

    /*
     * running the spring scheduler on each every 10 sec.
     * */
    @Scheduled(cron = "*/10 * * * * *")
    public void updateDeliveryBoyStatus(){
        updateDeliveryBoyQueue();
        findAllCreatedOrders();
        if(!(deliveryBoyQueue == null || deliveryBoyQueue.isEmpty())) {
            log.info("updated queue of delivery agent now available is "+deliveryBoyQueue.size());
            assigningOrdersToDeliveryBoys();
        }
    }

    /*
    * Fetch all the newly created order from database ORDER Table and placing into queue.
    * */
    private void findAllCreatedOrders(){
        List<Order> orders=orderRepository.findAllByStatus(ORDER_STATUS_CREATED);
        if(orders != null || (!orders.isEmpty())){
            orders.forEach(order -> {
                orderQueue.add(order);
            });
        }
    }

    /*
     * Fetch all available Agents from database DeliveryBoy Table and placing into queue.
     * */
    private void updateDeliveryBoyQueue(){
        List<DeliveryBoy> deliveryBoys=deliveryAgentRepository.findByAvailableTimeBefore(ZonedDateTime.now());
        if(deliveryBoys != null || (!deliveryBoys.isEmpty())){
            deliveryBoys.forEach(deliveryBoy -> {
                deliveryBoy.setAvailable(DELIVERY_BOY_AVAILABLE);
                deliveryAgentRepository.save(deliveryBoy);
                orderService.updateOrderStatus(deliveryBoy.getPhoneNo());
                if(!deliveryBoyQueue.contains(deliveryBoy))
                deliveryBoyQueue.add(deliveryBoy);
            });
        }
    }


    /*
     * Placing new order on availability of agents and newly created orders.
     * */
    private void assigningOrdersToDeliveryBoys(){
        var isOrderCreated = true;
        while (!deliveryBoyQueue.isEmpty() && isOrderCreated){
            if(!orderQueue.isEmpty()){
                DeliveryBoy deliveryBoy =deliveryBoyQueue.poll();
                Order order=orderQueue.poll();
                log.info("Publishing orders with delivery boys "+deliveryBoy.getPhoneNo()+" order id "+order.getId());
                orderService.assigningOrdersToDeliveryBoys(deliveryBoy, order);
            }else {
                isOrderCreated=false;
                log.info("No new order is created.");
            }
        }

    }
}
