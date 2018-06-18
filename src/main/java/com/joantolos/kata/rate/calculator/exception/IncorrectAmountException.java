package com.joantolos.kata.rate.calculator.exception;

public class IncorrectAmountException extends RuntimeException {

    public IncorrectAmountException() {
        super(ErrorMessage.INCORRECT_AMOUNT);
    }
}
