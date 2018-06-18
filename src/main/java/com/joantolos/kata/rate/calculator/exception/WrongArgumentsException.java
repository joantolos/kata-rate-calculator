package com.joantolos.kata.rate.calculator.exception;

public class WrongArgumentsException extends RuntimeException {

    public WrongArgumentsException() {
        super(ErrorMessage.WRONG_ARGUMENTS);
    }
}
