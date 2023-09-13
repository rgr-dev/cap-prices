package com.example.democap.controller;

import com.example.democap.entity.Price;
import com.example.democap.service.PriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/prices")
public class PriceController {

    private final  PriceService priceService;

    @GetMapping
    private Price getPriceByParams(
            @RequestParam(name = "applyDate", required = true) String applyDate,
            @RequestParam(name = "productId", required = true) Long productId,
            @RequestParam(name = "brandId", required = true) Long brandId){
        return priceService.getPriceDetail(applyDate, productId, brandId);
    }

}
