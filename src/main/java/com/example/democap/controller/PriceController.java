package com.example.democap.controller;

import com.example.democap.dto.PriceRequestDTO;
import com.example.democap.dto.PriceResponseDTO;
import com.example.democap.entity.Price;
import com.example.democap.service.PriceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/prices")
public class PriceController {

    private final  PriceService priceService;

    @GetMapping
    public PriceResponseDTO getPriceByParams(
            @Valid @ModelAttribute PriceRequestDTO priceRequestDTO){
        return priceService.getPriceDetail(priceRequestDTO.getApplyDate(), priceRequestDTO.getProductId(), priceRequestDTO.getBrandId());
    }

}
