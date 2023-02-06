package com.food.delivery.repository;

import com.food.delivery.entity.DeliveryBoy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.ZonedDateTime;
import java.util.List;

public interface DeliveryAgentRepository extends JpaRepository<DeliveryBoy, Long> {

    @Query("SELECT d FROM DeliveryBoy d "
            + "		WHERE d.isAvailable = :isAvailable")
     List<DeliveryBoy> getByStatus(final Boolean isAvailable);

     List<DeliveryBoy> findByAvailableTimeBefore(final ZonedDateTime availableTime);
}
