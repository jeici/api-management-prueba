package com.api.management.prueba.repository;

import com.api.management.prueba.model.Logistic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogisticRepository extends JpaRepository<Logistic, Long> {
    List<Logistic> findByNameContaining(String name);
    List<Logistic> findByNameContainingAndStatusIsTrue(String name);
    Logistic findByStatus(Boolean status);
}