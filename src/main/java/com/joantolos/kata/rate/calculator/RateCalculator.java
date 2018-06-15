package com.joantolos.kata.rate.calculator;

import com.joantolos.kata.rate.calculator.domain.LoanProvider;
import com.joantolos.kata.rate.calculator.exception.WrongArgumentsException;

public class RateCalculator {

    public static void main(String[] args) throws WrongArgumentsException {
        System.out.println(new LoanProvider(args).provide().toString());
    }
}
