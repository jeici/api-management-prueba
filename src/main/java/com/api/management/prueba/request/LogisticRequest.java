package com.api.management.prueba.request;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;

public class LogisticRequest implements Serializable {

    @NotBlank(message = "valor requerido")
    @NotNull(message = "valor requerido")
    @Size(max = 100)
    private String name;
    private String description;
    private BigDecimal discountRate;
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

    public BigDecimal getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(BigDecimal discountRate) {
        this.discountRate = discountRate;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
