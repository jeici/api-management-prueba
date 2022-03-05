package com.api.management.prueba.repository;

import com.api.management.prueba.model.Customer;
import com.api.management.prueba.model.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
    List<Delivery> findByCustomerId(Customer customerId);
    List<Delivery> findByCustomerIdAndStatusIsTrue(Customer customerId);
}