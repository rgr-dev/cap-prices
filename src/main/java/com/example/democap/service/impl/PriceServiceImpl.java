package com.example.democap.service.impl;

import com.example.democap.dto.PriceResponseDTO;
import com.example.democap.entity.Price;
import com.example.democap.exception.PriceNotFoundException;
import com.example.democap.repository.PriceRepository;
import com.example.democap.service.PriceService;
import com.example.democap.utils.DateFormats;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PriceServiceImpl implements PriceService {

    private final Comparator<Price> priceComparator = Comparator.comparingInt(Price::getPriority);

    private final PriceRepository priceRepository;


    @Override
    public PriceResponseDTO getPriceDetail(String applyDate, Long productId, Long brandId) {
        var applyDateTime = LocalDateTime.parse(applyDate, DateFormats.DATE_TIME_FORMATTER);
        var pricesResultList = priceRepository
                .findAllByStartDateBeforeAndEndDateAfterAndProductIdEqualsAndBrandIdEquals(applyDateTime, applyDateTime, productId, brandId);
        if (pricesResultList.isEmpty()) {
            throw new PriceNotFoundException();
        }
        return pricesResultList.size() > 1 ? filterByPriority(pricesResultList) : parsePriceToDTO(pricesResultList.get(0));
    }

    private PriceResponseDTO filterByPriority(List<Price> prices) {
        return prices.stream().max(priceComparator).map(this::parsePriceToDTO)
                .orElseThrow(PriceNotFoundException::new);
    }

    private PriceResponseDTO parsePriceToDTO(Price price) {
        return PriceResponseDTO.builder()
                .productId(price.getProductId())
                .brandId(price.getBrandId())
                .price(price.getPrice())
                .priceList(price.getPriceList())
                .startDate(price.getStartDate())
                .endDate(price.getEndDate()).build();
    }
}
