package com.api.management.prueba.repository;

import com.api.management.prueba.model.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {
    List<Warehouse> findByNameContaining(String name);
    List<Warehouse> findByNameContainingAndStatusIsTrue(String name);
    Warehouse findByType(Integer type);
    Warehouse findByStatus(Boolean status);
}