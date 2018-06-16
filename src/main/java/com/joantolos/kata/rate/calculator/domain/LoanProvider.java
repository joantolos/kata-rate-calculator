package com.joantolos.kata.rate.calculator.domain;

import com.joantolos.kata.rate.calculator.domain.entity.Arguments;
import com.joantolos.kata.rate.calculator.domain.entity.Borrower;
import com.joantolos.kata.rate.calculator.domain.entity.Loan;
import com.joantolos.kata.rate.calculator.exception.IncorrectAmountException;
import com.joantolos.kata.rate.calculator.exception.NotSufficientFoundsException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class LoanProvider {

    private List<Borrower> borrowers;
    private BigDecimal amount;

    public LoanProvider(String[] args) {
        this.borrowers = new BorrowersLoader().load(new ArgumentParser().parse(args, Arguments.MARKET_DATA_FILE_PATH));
        this.amount = new BigDecimal(new ArgumentParser().parse(args, Arguments.LOAN_AMOUNT));
    }

    public Loan provide() throws IncorrectAmountException, NotSufficientFoundsException {
        this.validateAmount();

        BigDecimal rate = new BigDecimal("0");
        BigDecimal monthlyRepayment = amount.divide(new BigDecimal(36), 2, BigDecimal.ROUND_HALF_UP);
        BigDecimal monthlyRate = new BigDecimal(0);
        BigDecimal totalRepayment = amount.add(monthlyRate.multiply(new BigDecimal(36)));

        return new Loan(amount, rate, monthlyRepayment, totalRepayment);
    }

    private void validateAmount() throws IncorrectAmountException, NotSufficientFoundsException {

        if(!calculateAvailableAmounts().contains(amount)) {
            throw new IncorrectAmountException("The amount should be any £100 increment between £1000 and £15000");
        }

        if(amount.compareTo(calculateTotalAvailable()) > 0) {
            throw new NotSufficientFoundsException("The market does not have sufficient offers from lenders to satisfy the loan.");
        }
    }

    private BigDecimal calculateTotalAvailable() {
        return borrowers.stream().map(Borrower::getAvailable).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private List<BigDecimal> calculateAvailableAmounts() {
        return new ArrayList<>();
    }
}
