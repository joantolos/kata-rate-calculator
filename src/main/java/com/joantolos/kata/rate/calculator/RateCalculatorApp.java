package com.joantolos.kata.rate.calculator;

import com.joantolos.kata.rate.calculator.core.RateCalculator;
import com.joantolos.kata.rate.calculator.exception.WrongArgumentsException;

public class RateCalculatorApp {

    public static void main(String[] args) throws WrongArgumentsException {
        System.out.println(new RateCalculator(args).getLoan().toString());
    }
}
