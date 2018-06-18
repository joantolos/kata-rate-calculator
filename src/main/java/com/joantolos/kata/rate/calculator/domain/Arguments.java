package com.joantolos.kata.rate.calculator.domain;

import com.joantolos.kata.rate.calculator.exception.WrongArgumentsException;

public enum Arguments {
    MARKET_DATA_FILE_PATH(0),
    LOAN_AMOUNT(1);

    private Integer index;

    Arguments(Integer index) {
        this.index = index;
    }

    public static String parse(String[] args, Arguments argument) throws WrongArgumentsException {
        try {
            return args[argument.index];
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            throw new WrongArgumentsException();
        }
    }
}
