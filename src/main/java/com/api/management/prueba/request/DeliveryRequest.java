package com.api.management.prueba.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
public class DeliveryRequest implements Serializable {

    @NotNull(message = "Id cliente es requerido")
    private Long customerId;

    @NotNull(message = "Id bodega es requerido")
    private Long warehouseId;

    @NotNull(message = "Id logistica es requerido")
    private Long logisticId;

    @NotNull(message = "Id producto es requerido")
    private Long productId;

    @NotNull(message = "precio es requerido")
    private BigDecimal price;

    @NotNull(message = "fecha de entrega es requerido")
    private String deliveryDate;

    @NotNull(message = "descuento es requerido")
    private BigDecimal discount;

    @NotNull(message = "quantity es requerido")
    private Integer quantity;

    @NotNull(message = "total es requerido")
    private BigDecimal total;

    @NotNull(message = "numero de transporte es requerido")
    private String transportNumber;

//    @NotNull(message = "numero de rastreo es requerido")
//    private String trakingNumber;

}
