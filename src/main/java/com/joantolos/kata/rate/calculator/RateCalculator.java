package com.joantolos.kata.rate.calculator;

import com.joantolos.kata.rate.calculator.domain.LoanProvider;
import com.joantolos.kata.rate.calculator.exception.NotSufficientFoundsException;
import com.joantolos.kata.rate.calculator.exception.WrongArgumentsException;

public class RateCalculator {

    public static void main(String[] args) throws WrongArgumentsException {
        try {
            System.out.println(new LoanProvider(args).provide().toString());
        } catch (NotSufficientFoundsException e) {
            System.out.println(e.getMessage());
        }
    }
}
