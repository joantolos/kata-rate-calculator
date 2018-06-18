package com.joantolos.kata.rate.calculator.exception;

public class MarketDataFileLoadingException extends RuntimeException {

    public MarketDataFileLoadingException() {
        super(ErrorMessage.MARKET_FILE_LOADING);
    }
}
