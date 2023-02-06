package com.food.delivery.repository;

import com.food.delivery.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("SELECT o FROM Order o "
            + "		WHERE o.status = :orderStatusCreated")
    List<Order> findAllByStatus(final String orderStatusCreated);

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE ORDER_TBL SET status= 'Delivered' WHERE status = 'In-Progress' and delivery_agent_phoneNo =?1")
    void updateStatus(final String phoneNo);
}
