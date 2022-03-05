package com.api.management.prueba.request;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;

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

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Long warehouseId) {
        this.warehouseId = warehouseId;
    }

    public Long getLogisticId() {
        return logisticId;
    }

    public void setLogisticId(Long logisticId) {
        this.logisticId = logisticId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getTransportNumber() {
        return transportNumber;
    }

    public void setTransportNumber(String transportNumber) {
        this.transportNumber = transportNumber;
    }
}
