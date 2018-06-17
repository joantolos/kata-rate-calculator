package com.joantolos.kata.rate.calculator.service;

import com.joantolos.kata.rate.calculator.ArgumentParser;
import com.joantolos.kata.rate.calculator.BorrowersLoader;
import com.joantolos.kata.rate.calculator.InputValidator;
import com.joantolos.kata.rate.calculator.domain.Arguments;
import com.joantolos.kata.rate.calculator.domain.Borrower;
import com.joantolos.kata.rate.calculator.domain.Loan;
import com.joantolos.kata.rate.calculator.exception.IncorrectAmountException;
import com.joantolos.kata.rate.calculator.exception.MarketDataFileLoadingException;
import com.joantolos.kata.rate.calculator.exception.NotSufficientFoundsException;
import com.joantolos.kata.rate.calculator.exception.WrongArgumentsException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class LoanService {

    public static final int NUM_MONTH = 36;
    private final Integer MIN_LOAN = 1000;
    private final Integer MAX_LOAN = 15000;
    private final Integer INCREMENT = 100;

    public Loan provide(String[] args) throws WrongArgumentsException, MarketDataFileLoadingException,
            NotSufficientFoundsException, IncorrectAmountException {

        InputValidator validator = new InputValidator(MIN_LOAN, MAX_LOAN, INCREMENT);
        validator.validateArgs(args);

        List<Borrower> borrowers = new BorrowersLoader().load(new ArgumentParser().parse(args, Arguments.MARKET_DATA_FILE_PATH));
        BigDecimal amount = new BigDecimal(new ArgumentParser().parse(args, Arguments.LOAN_AMOUNT));

        validator.validateLoanAmount(amount, borrowers);

        return this.getLoan(borrowers, amount);
    }

    //Total repayment formula: A = P (1 + r/n)^(nt)
    protected Loan getLoan(List<Borrower> borrowers, BigDecimal amount) {
        BigDecimal rate = this.getRate(borrowers, amount);
        BigDecimal totalRepayment = amount.multiply(rate.divide(new BigDecimal(12), RoundingMode.UP).add(new BigDecimal(1))).pow(NUM_MONTH);
        BigDecimal monthlyRepayment = totalRepayment.divide(new BigDecimal(NUM_MONTH), BigDecimal.ROUND_UP);

        return new Loan(amount, rate, monthlyRepayment, totalRepayment);
    }

    protected BigDecimal getRate(List<Borrower> borrowers, BigDecimal amount) {
        return new BigDecimal(0);
    }
}
