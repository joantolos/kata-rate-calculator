package com.joantolos.kata.rate.calculator.MarketDataLoader.domain

import com.joantolos.kata.rate.calculator.domain.ArgumentParser
import com.joantolos.kata.rate.calculator.domain.BorrowersLoader
import com.joantolos.kata.rate.calculator.domain.LoanProvider
import com.joantolos.kata.rate.calculator.domain.entity.Arguments
import com.joantolos.kata.rate.calculator.domain.entity.Borrower
import com.joantolos.kata.rate.calculator.domain.entity.Loan
import spock.lang.Shared
import spock.lang.Specification

class LoanProviderSpec extends Specification {

    @Shared String[] args
    @Shared List<Borrower> borrowers
    @Shared BigDecimal amount

    def setupSpec() {
        String fakeDataPath = new File(this.getClass().getResource('/mockedMarketData.csv').toURI()).getAbsolutePath()
        args = [fakeDataPath, "1000"]
        borrowers = new BorrowersLoader().load(new ArgumentParser().parse(args, Arguments.MARKET_DATA_FILE_PATH));
        amount = new BigDecimal(new ArgumentParser().parse(args, Arguments.LOAN_AMOUNT));
    }

    def 'Loan provider should respond with a loan validating the input' () {
        expect: 'the rate calculator should provide a loan'
        LoanProvider loanProvider = new LoanProvider()
        Loan loan = loanProvider.provide(args)
        loan != null

        and: 'the loan can be printed'
        loan.toString()
    }

}
