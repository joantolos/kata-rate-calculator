package com.joantolos.kata.rate.calculator.exception;

public interface ErrorMessage {
    String INCORRECT_AMOUNT = "The amount should be any £100 increment between £1000 and £15000";
    String MARKET_FILE_LOADING = "Wrong market data file provided!";
    String NOT_SUFFICIENT_FOUNDS = "The market does not have sufficient offers from lenders to satisfy the loan.";
    String ILLEGAL_ARGUMENT = "Incorrect arguments!";
}
