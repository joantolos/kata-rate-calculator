package com.joantolos.kata.rate.calculator.core;

import com.joantolos.kata.rate.calculator.domain.Borrower;
import com.joantolos.kata.rate.calculator.domain.Loan;

import java.math.BigDecimal;
import java.util.List;

public class RateCalculator {

    private List<Borrower> borrowers;
    private BigDecimal amount;

    public RateCalculator(String[] args) {
        this.borrowers = new BorrowerLoader().load(new Argument().get(args, Arguments.MARKET_DATA_FILE_PATH));
        this.amount = new BigDecimal(new Argument().get(args, Arguments.LOAN_AMOUNT));
    }

    public Loan getLoan() {
        BigDecimal totalRepayment = new BigDecimal("0");
        BigDecimal monthlyRepayment = new BigDecimal("0");
        BigDecimal rate = new BigDecimal("0");
        return new Loan(amount, rate, monthlyRepayment, totalRepayment);
    }
}
