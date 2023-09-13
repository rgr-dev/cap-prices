package com.example.democap.repository;

import com.example.democap.entity.Price;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface PriceRepository extends JpaRepository<Price, Long> {

    List<Price> findAllByStartDateBeforeAndEndDateAfterAndProductIdEqualsAndBrandIdEquals(LocalDateTime applicationDateStart, LocalDateTime applicationDateEnd, Long productId, Long brandId);

}
