package com.example.democap.service.impl;

import com.example.democap.dto.PriceResponseDTO;
import com.example.democap.entity.Price;
import com.example.democap.exception.PriceNotFoundException;
import com.example.democap.repository.PriceRepository;
import com.example.democap.service.PriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PriceServiceImpl implements PriceService {

    private static final String PRICE_NOT_FOUND_ERROR = "Not published prices for the specified product";

    private final PriceRepository priceRepository;

    private final Comparator<Price> priceComparator = (pOne, pTwo) -> pOne.getPriority() > pTwo.getPriority() ? pOne.getPriority(): pTwo.getPriority();

    @Override
    public PriceResponseDTO getPriceDetail(String applyDate, Long productId, Long brandId) {
        var applyDateTime = LocalDateTime.parse(applyDate, DateTimeFormatter.ISO_DATE_TIME);
        var pricesResultList = priceRepository
                .findAllByStartDateBeforeAndEndDateAfterAndProductIdEqualsAndBrandIdEquals(applyDateTime, applyDateTime, productId, brandId);
        if(pricesResultList.isEmpty()){
            throw new PriceNotFoundException(PRICE_NOT_FOUND_ERROR);
        }
        return pricesResultList.size() > 1 ? filterByPriority(pricesResultList): parsePriceToDTO(pricesResultList.get(0));
    }

    private PriceResponseDTO filterByPriority(List<Price> prices){
        return prices.stream().max(priceComparator).map(this::parsePriceToDTO)
                .orElseThrow(() -> new PriceNotFoundException(PRICE_NOT_FOUND_ERROR));
    }

    private PriceResponseDTO parsePriceToDTO(Price price){
        return PriceResponseDTO.builder()
                .productId(price.getProductId())
                .brandId(price.getBrandId())
                .price(price.getPrice())
                .priceList(price.getPriceList())
                .startDate(price.getStartDate())
                .endDate(price.getEndDate()).build();
    }
}
