package com.example.democap.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "PRICES")
public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="BRAND_ID")
    private Long brandId;

    @Column(name="START_DATE")
    private LocalDateTime startDate;

    @Column(name="END_DATE")
    private LocalDateTime endDate;

    @Column(name="PRICE_LIST")
    private Short priceList;

    @Column(name="PRODUCT_ID")
    private Long productId;

    @Column(name="PRIORITY")
    private Short priority;

    @Column(name="PRICE")
    private Double price;

    @Column(name="CURR")
    private String currency;

}
