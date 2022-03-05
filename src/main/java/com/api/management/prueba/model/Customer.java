package com.api.management.prueba.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@Table(name = "customers", schema = "logistic_prueba")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Valor requerido")
    @Size(max = 100)
    @Column(name="full_name")
    private String fullName;

    @Column(name = "phone")
    private String phone;

    @NotBlank(message = "el email es requerido")
    @Email(message = "email no es valido")
    @Column(name = "email")
    private String email;

    @Column(name="address")
    private String address;

    @Column(name="status")
    private Boolean status;

    public Customer() {
    }

    public Customer(String fullName, String phone, String email, String address, Boolean status) {
        this.fullName = fullName;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.status = status;
    }
}
