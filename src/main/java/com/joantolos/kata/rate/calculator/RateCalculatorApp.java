package com.joantolos.kata.rate.calculator;

import com.joantolos.kata.rate.calculator.domain.LoanProvider;
import com.joantolos.kata.rate.calculator.exception.IncorrectAmountException;
import com.joantolos.kata.rate.calculator.exception.NotSufficientFoundsException;
import com.joantolos.kata.rate.calculator.exception.WrongArgumentsException;

public class RateCalculatorApp {

    public static void main(String[] args) {
        try {
            System.out.println(new LoanProvider(args).provide().toString());
        } catch (WrongArgumentsException | IncorrectAmountException | NotSufficientFoundsException e) {
            System.out.println(e.getMessage());
        }
    }
}
