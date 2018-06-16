package com.joantolos.kata.rate.calculator.domain;

import com.joantolos.kata.rate.calculator.domain.entity.Arguments;
import com.joantolos.kata.rate.calculator.domain.entity.Borrower;
import com.joantolos.kata.rate.calculator.domain.entity.Loan;
import com.joantolos.kata.rate.calculator.exception.IncorrectAmountException;
import com.joantolos.kata.rate.calculator.exception.MarketDataFileLoadingException;
import com.joantolos.kata.rate.calculator.exception.NotSufficientFoundsException;
import com.joantolos.kata.rate.calculator.exception.WrongArgumentsException;

import java.math.BigDecimal;
import java.util.List;

public class LoanProvider {

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

    protected Loan getLoan(List<Borrower> borrowers, BigDecimal amount) {
        BigDecimal rate = this.getRate(borrowers, amount);
        BigDecimal monthlyRepayment = amount.divide(new BigDecimal(36), 2, BigDecimal.ROUND_HALF_UP);
        BigDecimal monthlyRate = new BigDecimal(0);
        BigDecimal totalRepayment = amount.add(monthlyRate.multiply(new BigDecimal(36)));

        return new Loan(amount, rate, monthlyRepayment, totalRepayment);
    }

    protected BigDecimal getRate(List<Borrower> borrowers, BigDecimal amount) {
        return new BigDecimal(0);
    }
}
