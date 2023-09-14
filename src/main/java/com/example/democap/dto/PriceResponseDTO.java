package com.example.democap.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PriceResponseDTO {
    private Long productId;
    private Long brandId;
    private Short priceList;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Double price;
}

