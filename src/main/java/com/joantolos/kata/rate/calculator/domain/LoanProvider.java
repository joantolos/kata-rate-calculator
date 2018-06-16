package com.joantolos.kata.rate.calculator.domain;

import com.joantolos.kata.rate.calculator.domain.entity.Arguments;
import com.joantolos.kata.rate.calculator.domain.entity.Borrower;
import com.joantolos.kata.rate.calculator.domain.entity.Loan;
import com.joantolos.kata.rate.calculator.exception.NotSufficientFoundsException;

import java.math.BigDecimal;
import java.util.List;

public class LoanProvider {

    private List<Borrower> borrowers;
    private BigDecimal amount;
    private BigDecimal totalAvailable;

    public LoanProvider(String[] args) {
        this.borrowers = new BorrowersLoader().load(new ArgumentParser().parse(args, Arguments.MARKET_DATA_FILE_PATH));
        this.amount = new BigDecimal(new ArgumentParser().parse(args, Arguments.LOAN_AMOUNT));
        this.totalAvailable = this.calculateTotalAvailable(this.borrowers);
    }

    private BigDecimal calculateTotalAvailable(List<Borrower> borrowers) {
        return borrowers.stream().map(Borrower::getAvailable).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public Loan provide() throws NotSufficientFoundsException {

        if(amount.compareTo(totalAvailable) > 0) {
            throw new NotSufficientFoundsException("The market does not have sufficient offers from lenders to satisfy the loan.");
        }

        BigDecimal rate = new BigDecimal("0");
        BigDecimal monthlyRepayment = amount.divide(new BigDecimal(36), 2, BigDecimal.ROUND_HALF_UP);
        BigDecimal monthlyRate = new BigDecimal(0);
        BigDecimal totalRepayment = amount.add(monthlyRate.multiply(new BigDecimal(36)));

        return new Loan(amount, rate, monthlyRepayment, totalRepayment);
    }
}
