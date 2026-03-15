package com.shruti.supply_chain.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "supplier_products")
public class SupplierProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // supplier providing the product
    @ManyToOne
    @JoinColumn(name = "supplier_id", nullable = false)
    private SupplierProfile supplier;

    // product being supplied
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    // price at which supplier sells the product
    @Column(nullable = false)
    private BigDecimal supplyPrice;

    // minimum quantity supplier accepts per order
    private Integer minimumOrderQuantity;

    // delivery lead time in days
    private Integer leadTimeDays;

    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
}