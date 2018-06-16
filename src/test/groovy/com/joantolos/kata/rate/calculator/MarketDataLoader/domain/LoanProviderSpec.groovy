package com.joantolos.kata.rate.calculator.MarketDataLoader.domain

import com.joantolos.kata.rate.calculator.domain.ArgumentParser
import com.joantolos.kata.rate.calculator.domain.BorrowersLoader
import com.joantolos.kata.rate.calculator.domain.LoanProvider
import com.joantolos.kata.rate.calculator.domain.entity.Arguments
import com.joantolos.kata.rate.calculator.domain.entity.Borrower
import com.joantolos.kata.rate.calculator.domain.entity.Loan
import spock.lang.Specification

class LoanProviderSpec extends Specification {

    private final String fakeDataPath = new File(this.getClass().getResource('/mockedMarketData.csv').toURI()).getAbsolutePath()

    def 'Loan provider should respond with a loan' () {
        given: 'a fake file located on resources and a loan request of 1000'
        String[] args = [fakeDataPath, "1000"]
        List<Borrower> borrowers = new BorrowersLoader().load(new ArgumentParser().parse(args, Arguments.MARKET_DATA_FILE_PATH));
        BigDecimal amount = new BigDecimal(new ArgumentParser().parse(args, Arguments.LOAN_AMOUNT));

        expect: 'the rate calculator should provide a loan'
        LoanProvider rateCalculator = new LoanProvider()
        Loan loan = rateCalculator.provide(borrowers, amount)
        loan != null

        and: 'the loan can be printed'
        loan.toString()
    }

}
