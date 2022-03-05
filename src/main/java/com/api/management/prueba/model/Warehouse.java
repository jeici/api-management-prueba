package com.api.management.prueba.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@Table(name = "warehouses", schema = "logistic_prueba")
public class Warehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Valor requerido")
    @Size(max = 100)
    @Column(name="name")
    private String name;

    @Min(value=0, message = " solo acepta valores positivos" )
    @Column(name="type")
    private Integer type;

    @Column(name="status")
    private Boolean status;

    @Column(name="description")
    private String description;

    public Warehouse(String name, Integer type, Boolean status, String description) {
        this.name = name;
        this.type = type;
        this.status = status;
        this.description = description;
    }

    public Warehouse() {
    }
}
