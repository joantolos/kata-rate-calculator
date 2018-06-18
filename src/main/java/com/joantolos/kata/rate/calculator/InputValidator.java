package com.joantolos.kata.rate.calculator;

import com.joantolos.kata.rate.calculator.domain.Arguments;
import com.joantolos.kata.rate.calculator.domain.Lender;
import com.joantolos.kata.rate.calculator.exception.IncorrectAmountException;
import com.joantolos.kata.rate.calculator.exception.NotSufficientFoundsException;
import com.joantolos.kata.rate.calculator.exception.WrongArgumentsException;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class InputValidator {

    private List<BigDecimal> availableLoans;

    public InputValidator(Integer minLoan, Integer maxLoan, Integer increment) {
        this.availableLoans = IntStream.iterate(minLoan, i -> i + increment)
                .limit(maxLoan / increment)
                .boxed()
                .map(BigDecimal::new)
                .collect(Collectors.toList());
    }

    public void validateArgs(String[] args) throws WrongArgumentsException {
        ArgumentParser argumentParser = new ArgumentParser();
        argumentParser.parse(args, Arguments.MARKET_DATA_FILE_PATH);
        argumentParser.parse(args, Arguments.LOAN_AMOUNT);
    }

    public void validateLoanAmount(BigDecimal amount, List<Lender> lenders) throws IncorrectAmountException, NotSufficientFoundsException {
        if(amount.compareTo(getTotalAvailable(lenders)) > 0) {
            throw new NotSufficientFoundsException("The market does not have sufficient offers from lenders to satisfy the loan.");
        }

        if(!availableLoans.contains(amount)) {
            throw new IncorrectAmountException("The amount should be any £100 increment between £1000 and £15000");
        }

    }

    private BigDecimal getTotalAvailable(List<Lender> lenders) {
        return lenders.stream().map(Lender::getAvailable).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
