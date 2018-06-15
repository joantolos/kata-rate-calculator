package com.joantolos.kata.rate.calculator.core;

import com.joantolos.kata.rate.calculator.exception.WrongArgumentsException;

public class Argument {

    public String get(String[] args, Arguments argument) throws WrongArgumentsException {
        try {
            switch (argument) {
                case MARKET_DATA_FILE_PATH:
                    return args[0];
                case LOAN_AMOUNT:
                    return args[1];
            }
        } catch (Exception e) {
            throw new WrongArgumentsException(e.getMessage());
        }
        throw new WrongArgumentsException("Unknown argument required");
    }
}
