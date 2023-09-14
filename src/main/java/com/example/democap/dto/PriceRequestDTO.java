package com.example.democap.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PriceRequestDTO {

    // anotar con @Regex para asegurar el formato de fecha
    @NotNull
    private String applyDate;
    @NotNull
    private Long productId;
    @NotNull
    private Long brandId;

}
