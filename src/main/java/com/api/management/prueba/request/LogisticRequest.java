package com.api.management.prueba.request;

import javax.validation.constraints.*;
import java.io.Serializable;

public class LogisticRequest implements Serializable {

    @NotBlank(message = "valor requerido")
    @NotNull(message = "valor requerido")
    @Size(max = 100)
    private String name;
    private String description;

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
}
