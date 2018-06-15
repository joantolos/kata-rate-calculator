package com.joantolos.kata.rate.calculator.domain;

import com.joantolos.kata.rate.calculator.domain.entity.Arguments;
import com.joantolos.kata.rate.calculator.domain.entity.Borrower;
import com.joantolos.kata.rate.calculator.domain.entity.Loan;

import java.math.BigDecimal;
import java.util.List;

public class LoanProvider {

    private List<Borrower> borrowers;
    private BigDecimal amount;

    public LoanProvider(String[] args) {
        this.borrowers = new BorrowerLoader().load(new ArgumentParser().parse(args, Arguments.MARKET_DATA_FILE_PATH));
        this.amount = new BigDecimal(new ArgumentParser().parse(args, Arguments.LOAN_AMOUNT));
    }

    public Loan provide() {
        BigDecimal totalRepayment = new BigDecimal("0");
        BigDecimal monthlyRepayment = new BigDecimal("0");
        BigDecimal rate = new BigDecimal("0");
        return new Loan(amount, rate, monthlyRepayment, totalRepayment);
    }
}
