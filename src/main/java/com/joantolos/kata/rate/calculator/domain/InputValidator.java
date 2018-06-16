package com.joantolos.kata.rate.calculator.domain;

import com.joantolos.kata.rate.calculator.domain.entity.Arguments;
import com.joantolos.kata.rate.calculator.domain.entity.Borrower;
import com.joantolos.kata.rate.calculator.exception.IncorrectAmountException;
import com.joantolos.kata.rate.calculator.exception.NotSufficientFoundsException;
import com.joantolos.kata.rate.calculator.exception.WrongArgumentsException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class InputValidator {

    public void validateArgs(String[] args) throws WrongArgumentsException {
        ArgumentParser argumentParser = new ArgumentParser();
        argumentParser.parse(args, Arguments.MARKET_DATA_FILE_PATH);
        argumentParser.parse(args, Arguments.LOAN_AMOUNT);
    }

    public void validateLoanAmount(BigDecimal amount, List<Borrower> borrowers) throws IncorrectAmountException, NotSufficientFoundsException {
        if(!calculateAvailableAmounts().contains(amount)) {
            throw new IncorrectAmountException("The amount should be any £100 increment between £1000 and £15000");
        }

        if(amount.compareTo(calculateTotalAvailable(borrowers)) > 0) {
            throw new NotSufficientFoundsException("The market does not have sufficient offers from lenders to satisfy the loan.");
        }
    }

    private BigDecimal calculateTotalAvailable(List<Borrower> borrowers) {
        return borrowers.stream().map(Borrower::getAvailable).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private List<BigDecimal> calculateAvailableAmounts() {
        List<BigDecimal> availableAmounts = new ArrayList<>();
        availableAmounts.add(new BigDecimal(1000));
        availableAmounts.add(new BigDecimal(10000000));
        return availableAmounts;
    }
}
