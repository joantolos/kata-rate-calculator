package com.joantolos.kata.rate.calculator.validator;

import com.joantolos.kata.rate.calculator.domain.Arguments;
import com.joantolos.kata.rate.calculator.domain.Lender;
import com.joantolos.kata.rate.calculator.exception.IncorrectAmountException;
import com.joantolos.kata.rate.calculator.exception.NotSufficientFoundsException;

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

    public void validateArgs(String[] args) throws IllegalArgumentException {
        Arguments.parse(args, Arguments.MARKET_DATA_FILE_PATH);
        Arguments.parse(args, Arguments.LOAN_AMOUNT);
    }

    public void validateLoanAmount(List<Lender> lenders, BigDecimal amount) throws IncorrectAmountException, NotSufficientFoundsException {
        if(amount.compareTo(getTotalAvailable(lenders)) > 0) {
            throw new NotSufficientFoundsException();
        }

        if(!availableLoans.contains(amount)) {
            throw new IncorrectAmountException();
        }
    }

    private BigDecimal getTotalAvailable(List<Lender> lenders) {
        return lenders.stream().map(Lender::getAvailable).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
