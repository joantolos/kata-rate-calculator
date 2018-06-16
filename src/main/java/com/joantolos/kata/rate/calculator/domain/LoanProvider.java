package com.joantolos.kata.rate.calculator.domain;

import com.joantolos.kata.rate.calculator.domain.entity.Borrower;
import com.joantolos.kata.rate.calculator.domain.entity.Loan;

import java.math.BigDecimal;
import java.util.List;

public class LoanProvider {

    public Loan provide(List<Borrower> borrowers, BigDecimal amount) {
        BigDecimal rate = new BigDecimal("0");
        BigDecimal monthlyRepayment = amount.divide(new BigDecimal(36), 2, BigDecimal.ROUND_HALF_UP);
        BigDecimal monthlyRate = new BigDecimal(0);
        BigDecimal totalRepayment = amount.add(monthlyRate.multiply(new BigDecimal(36)));

        return new Loan(amount, rate, monthlyRepayment, totalRepayment);
    }
}
