package com.example.democap.exception;


public class PriceNotFoundException extends RuntimeException {

    private static final String PRICE_NOT_FOUND_ERROR = "Not published prices for the specified product";

    public PriceNotFoundException() {
        super(PRICE_NOT_FOUND_ERROR);
    }
}
