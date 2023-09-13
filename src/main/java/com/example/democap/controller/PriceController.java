package com.example.democap.controller;

import com.example.democap.service.PriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/prices")
public class PriceController {

    private final  PriceService priceService;

}
