package com.joantolos.kata.rate.calculator.domain;

import com.joantolos.kata.rate.calculator.domain.entity.Arguments;
import com.joantolos.kata.rate.calculator.exception.WrongArgumentsException;

public class ArgumentParser {

    public String parse(String[] args, Arguments argument) throws WrongArgumentsException {
        try {
            return args[argument.getIndex()];
        } catch (Exception e) {
            throw new WrongArgumentsException(e.getMessage());
        }
    }
}
