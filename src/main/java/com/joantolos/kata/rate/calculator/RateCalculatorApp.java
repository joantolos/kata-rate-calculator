package com.joantolos.kata.rate.calculator;

import com.joantolos.kata.rate.calculator.domain.ArgumentParser;
import com.joantolos.kata.rate.calculator.domain.BorrowersLoader;
import com.joantolos.kata.rate.calculator.domain.InputValidator;
import com.joantolos.kata.rate.calculator.domain.LoanProvider;
import com.joantolos.kata.rate.calculator.domain.entity.Arguments;
import com.joantolos.kata.rate.calculator.domain.entity.Borrower;
import com.joantolos.kata.rate.calculator.exception.IncorrectAmountException;
import com.joantolos.kata.rate.calculator.exception.NotSufficientFoundsException;
import com.joantolos.kata.rate.calculator.exception.WrongArgumentsException;

import java.math.BigDecimal;
import java.util.List;

public class RateCalculatorApp {

    public static void main(String[] args) {
        try {
            InputValidator validator = new InputValidator();
            validator.validateArgs(args);

            List<Borrower> borrowers = new BorrowersLoader().load(new ArgumentParser().parse(args, Arguments.MARKET_DATA_FILE_PATH));
            BigDecimal amount = new BigDecimal(new ArgumentParser().parse(args, Arguments.LOAN_AMOUNT));

            validator.validateLoanAmount(amount, borrowers);

            System.out.println(new LoanProvider().provide(borrowers, amount).toString());
        } catch (WrongArgumentsException | IncorrectAmountException | NotSufficientFoundsException e) {
            System.out.println(e.getMessage());
        }
    }
}
