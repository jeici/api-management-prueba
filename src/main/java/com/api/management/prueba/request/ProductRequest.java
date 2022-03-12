package com.api.management.prueba.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;

public class ProductRequest implements Serializable {
    @NotBlank(message = "nombre requerido")
    @NotNull(message = "nombre requerido")
    @Size(max = 100)
    private String name;
    @NotBlank(message = "descripcion requerido")
    @NotNull(message = "descripcion requerido")
    private String description;
    @NotBlank(message = "precio requerido")
    @NotNull(message = "precio requerido")
    private BigDecimal price;
    private Boolean status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
