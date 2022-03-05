package com.api.management.prueba.repository;

import com.api.management.prueba.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByNameContaining(String name);
    List<Product> findByNameContainingAndStatusIsTrue(String name);
    Product findByStatus(Boolean status);
}