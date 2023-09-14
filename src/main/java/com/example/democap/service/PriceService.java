package com.example.democap.service;

import com.example.democap.dto.PriceResponseDTO;

public interface PriceService {

    PriceResponseDTO getPriceDetail(String applyDate, Long productId, Long brandId);

}
