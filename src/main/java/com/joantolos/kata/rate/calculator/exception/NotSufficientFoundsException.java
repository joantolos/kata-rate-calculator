package com.joantolos.kata.rate.calculator.exception;

public class NotSufficientFoundsException extends RuntimeException {

    public NotSufficientFoundsException() {
        super(ErrorMessage.NOT_SUFFICIENT_FOUNDS);
    }
}
