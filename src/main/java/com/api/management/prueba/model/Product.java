package com.api.management.prueba.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Table(name = "products", schema = "logistic_prueba")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Valor requerido")
    @Size(max = 100)
    @Column(name="name")
    private String name;

    @Column(name="status")
    private Boolean status;

    @Column(name="description")
    private String description;

    @Column(name="price")
    private BigDecimal price;

    public Product(String name, Boolean status, String description, BigDecimal price) {
        this.name = name;
        this.status = status;
        this.description = description;
        this.price = price;
    }

    public Product() {
    }
}
