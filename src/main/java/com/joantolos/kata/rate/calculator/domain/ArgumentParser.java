package com.joantolos.kata.rate.calculator.domain;

import com.joantolos.kata.rate.calculator.domain.entity.Arguments;
import com.joantolos.kata.rate.calculator.exception.WrongArgumentsException;

public class ArgumentParser {

    public String parse(String[] args, Arguments argument) throws WrongArgumentsException {
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
