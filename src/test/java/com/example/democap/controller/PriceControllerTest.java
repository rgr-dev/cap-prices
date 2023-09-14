package com.example.democap.controller;

import com.example.democap.dto.PriceResponseDTO;
import com.example.democap.utils.DateFormats;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.time.LocalDateTime;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PriceControllerTest {

    @Value(value = "${local.server.port}")
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @ParameterizedTest
    @MethodSource("priceScenariosProvider")
    public void priceControllerParameterizedTests(
            String requestApplyDate,
            Long productId,
            LocalDateTime resultStartDate,
            LocalDateTime resultEndDate,
            Short priceList, Double price) {
        var brandId = (long) 1;

        PriceResponseDTO responseDTO = PriceResponseDTO.builder()
                .productId(productId)
                .brandId(brandId)
                .priceList(priceList)
                .startDate(resultStartDate).endDate(resultEndDate).price(price).build();

        var params = "applyDate=%s&productId=%d&brandId=%d".formatted(requestApplyDate, productId, brandId);
        assertThat(this.restTemplate.getForObject("http://localhost:%d/price?%s".formatted(port, params),
                PriceResponseDTO.class)).isEqualTo(responseDTO);
    }

    static Stream<Arguments> priceScenariosProvider() {
        return Stream.of(
                arguments("2020-06-14 10:00:00", (long) 35455, getFormattedDate("2020-06-14 00:00:00"), getFormattedDate("2020-12-31 23:59:59"), (short) 1, 35.50),
                arguments("2020-06-14 16:00:00", (long) 35455, getFormattedDate("2020-06-14 15:00:00"), getFormattedDate("2020-06-14 18:30:00"), (short) 2, 25.45),
                arguments("2020-06-14 21:00:00", (long) 35455, getFormattedDate("2020-06-14 00:00:00"), getFormattedDate("2020-12-31 23:59:59"), (short) 1, 35.50),
                arguments("2020-06-15 10:00:00", (long) 35455, getFormattedDate("2020-06-15 00:00:00"), getFormattedDate("2020-06-15 11:00:00"), (short) 3, 30.50),
                arguments("2020-06-16 21:00:00", (long) 35455, getFormattedDate("2020-06-15 16:00:00"), getFormattedDate("2020-12-31 23:59:59"), (short) 4, 38.95)

        );
    }

    private static LocalDateTime getFormattedDate(String data) {
        return LocalDateTime.parse(data, DateFormats.DATE_TIME_FORMATTER);
    }


}
