package com.example.democap.controller;

import com.example.democap.dto.PriceRequestDTO;
import com.example.democap.dto.PriceResponseDTO;
import com.example.democap.service.PriceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/price")
public class PriceController {

    private final PriceService priceService;

    @GetMapping
    public PriceResponseDTO getPriceByParams(
            @Valid @ModelAttribute PriceRequestDTO priceRequestDTO) {
        return priceService.getPriceDetail(priceRequestDTO.getApplyDate(), priceRequestDTO.getProductId(), priceRequestDTO.getBrandId());
    }

}
