package com.api.management.prueba.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.io.Serializable;

@Getter
@Setter
public class WarehouseRequest implements Serializable {

    @NotBlank(message = "valor requerido")
    @NotNull(message = "valor requerido")
    @Size(max = 100)
    private String name;
    @Min(value=0, message = "solo acepta valores positivos" )
    private Integer type;
    private String description;
}
