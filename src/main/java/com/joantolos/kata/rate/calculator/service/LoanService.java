package com.joantolos.kata.rate.calculator.service;

import com.joantolos.kata.rate.calculator.validator.InputValidator;
import com.joantolos.kata.rate.calculator.domain.Arguments;
import com.joantolos.kata.rate.calculator.domain.Lender;
import com.joantolos.kata.rate.calculator.domain.Loan;
import com.joantolos.kata.rate.calculator.exception.IncorrectAmountException;
import com.joantolos.kata.rate.calculator.exception.MarketDataFileLoadingException;
import com.joantolos.kata.rate.calculator.exception.NotSufficientFoundsException;
import com.joantolos.kata.rate.calculator.exception.WrongArgumentsException;

import java.math.BigDecimal;
import java.util.List;

public class LoanService {

    private final int NUM_MONTH = 36;
    private final Integer MIN_LOAN = 1000;
    private final Integer MAX_LOAN = 15000;
    private final Integer INCREMENT = 100;
    private final InputValidator validator;

    public LoanService(){
        this.validator = new InputValidator(MIN_LOAN, MAX_LOAN, INCREMENT);
    }

    public Loan provide(String[] args) throws WrongArgumentsException, MarketDataFileLoadingException,
            NotSufficientFoundsException, IncorrectAmountException {

        this.validator.validateArgs(args);

        return this.getLoan(
                new LoaderService().load(Arguments.parse(args, Arguments.MARKET_DATA_FILE_PATH)),
                new BigDecimal(Arguments.parse(args, Arguments.LOAN_AMOUNT)));
    }

    private Loan getLoan(List<Lender> lenders, BigDecimal amount) throws NotSufficientFoundsException, IncorrectAmountException {

        this.validator.validateLoanAmount(lenders, amount);

        BigDecimal borrowed = new BigDecimal(0);
        BigDecimal totalRepayment = new BigDecimal(0);

        for(Lender lender : lenders) {
            if(!borrowed.equals(amount) && lender.getAvailable().compareTo(amount) <= 0) {
                borrowed = borrowed.add(lender.getAvailable());
                if(borrowed.compareTo(amount) > 0) {
                    borrowed = borrowed.subtract(borrowed.subtract(amount));
                }
                totalRepayment = totalRepayment.add(lender.lend(borrowed));
            }
        }

        return new Loan(
                amount,
                this.getRate(borrowed, totalRepayment),
                this.getMonthlyRepayment(totalRepayment),
                totalRepayment);
    }

    private BigDecimal getRate(BigDecimal borrowed, BigDecimal totalRepayment) {
        return borrowed
                .divide(totalRepayment, 2, BigDecimal.ROUND_FLOOR)
                .subtract(new BigDecimal(1))
                .abs();
    }

    private BigDecimal getMonthlyRepayment(BigDecimal totalRepayment) {
        return totalRepayment.divide(new BigDecimal(NUM_MONTH), 2, BigDecimal.ROUND_FLOOR);
    }
}
