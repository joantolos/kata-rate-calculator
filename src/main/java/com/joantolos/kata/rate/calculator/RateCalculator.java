package com.joantolos.kata.rate.calculator;

import com.joantolos.kata.rate.calculator.domain.LoanProvider;
import com.joantolos.kata.rate.calculator.exception.IncorrectAmountException;
import com.joantolos.kata.rate.calculator.exception.MarketDataFileLoadingException;
import com.joantolos.kata.rate.calculator.exception.NotSufficientFoundsException;
import com.joantolos.kata.rate.calculator.exception.WrongArgumentsException;

public class RateCalculator {

    public static void main(String[] args) {
        try {

            System.out.println(new LoanProvider().provide(args).toString());

        } catch (MarketDataFileLoadingException |
                WrongArgumentsException |
                IncorrectAmountException |
                NotSufficientFoundsException e) {

            System.out.println(e.getMessage());
        }
    }
}
