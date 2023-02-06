package com.food.delivery.repository;

import com.food.delivery.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AddressRepository extends JpaRepository<Address, Long> {

    @Query(nativeQuery = true, value = "SELECT *FROM ADDRESS_TBL WHERE customer_phoneNo =?1 and postal_code =?2 LIMIT 1")
    public Address findByCustomerPhoneAndPostalCode(final String customerPhone, final String postalCode);
}
