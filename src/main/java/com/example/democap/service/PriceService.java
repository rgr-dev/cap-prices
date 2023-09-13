package com.example.democap.service;

import com.example.democap.entity.Price;

public interface PriceService {

    Price getPriceDetail(String applyDate, Long productId, Long brandId);

}
