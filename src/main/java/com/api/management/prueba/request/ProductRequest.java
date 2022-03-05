package com.api.management.prueba.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
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
}
