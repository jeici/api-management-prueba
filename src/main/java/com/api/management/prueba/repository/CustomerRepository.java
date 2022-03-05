package com.api.management.prueba.repository;

import com.api.management.prueba.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findByFullNameContaining(String fullName);
    List<Customer> findByFullNameContainingAndStatusIsTrue(String fullName);
}