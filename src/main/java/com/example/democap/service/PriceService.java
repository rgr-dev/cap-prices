package com.example.democap.service;

import com.example.democap.dto.PriceResponseDTO;
import com.example.democap.entity.Price;

public interface PriceService {

    PriceResponseDTO getPriceDetail(String applyDate, Long productId, Long brandId);

}
