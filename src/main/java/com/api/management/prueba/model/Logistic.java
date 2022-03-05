package com.api.management.prueba.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Entity
@Table(name = "logistics", schema = "logistic_prueba")
public class Logistic {
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

    @Column(name="discount_rate")
    private BigDecimal discountRate;

    public Logistic(String name, Boolean status, String description, BigDecimal discountRate) {
        this.name = name;
        this.status = status;
        this.description = description;
        this.discountRate = discountRate;
    }

    public Logistic() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(BigDecimal discountRate) {
        this.discountRate = discountRate;
    }
}
