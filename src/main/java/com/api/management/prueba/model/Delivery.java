package com.api.management.prueba.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "deliveries", schema = "logistic_prueba")
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customerId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "warehouse_id", referencedColumnName = "id")
    private Warehouse warehouseId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "logistic_id", referencedColumnName = "id")
    private Logistic logisticId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product productId;

    @Column
    private BigDecimal price;

    @Column
    private LocalDateTime deliveryDate;

    @Column
    private BigDecimal discount;

    @Column
    private Integer quantity;

    @Column
    private BigDecimal total;

    @Column
    private String transportNumber;

    @Column
    private String trakingNumber;

    @Column
    private Boolean status;

    public Delivery() {
    }

}
