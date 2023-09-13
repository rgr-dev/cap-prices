package com.example.democap.service.impl;

import com.example.democap.entity.Price;
import com.example.democap.repository.PriceRepository;
import com.example.democap.service.PriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PriceServiceImpl implements PriceService {

    private final PriceRepository priceRepository;

    @Override
    public Price getPriceDetail(String applyDate, Long productId, Long brandId) {
        return null;
    }
}
