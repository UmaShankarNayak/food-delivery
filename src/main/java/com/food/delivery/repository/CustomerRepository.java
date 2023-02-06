package com.food.delivery.repository;

import com.food.delivery.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
     Optional<Customer>  findByPhoneNo(String phone);
}
