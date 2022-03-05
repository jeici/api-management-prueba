package com.api.management.prueba.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
