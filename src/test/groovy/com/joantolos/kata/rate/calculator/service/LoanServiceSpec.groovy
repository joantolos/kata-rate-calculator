package com.joantolos.kata.rate.calculator.service

import com.joantolos.kata.rate.calculator.ArgumentParser
import com.joantolos.kata.rate.calculator.BorrowersLoader
import com.joantolos.kata.rate.calculator.domain.Arguments
import com.joantolos.kata.rate.calculator.domain.Borrower
import com.joantolos.kata.rate.calculator.domain.Loan
import spock.lang.Shared
import spock.lang.Specification

class LoanServiceSpec extends Specification {

    @Shared String[] args
    @Shared List<Borrower> borrowers
    @Shared BigDecimal amount

    def setupSpec() {
        String fakeDataPath = new File(this.getClass().getResource('/mockedMarketData.csv').toURI()).getAbsolutePath()
        args = [fakeDataPath, "1000"]
        borrowers = new BorrowersLoader().load(new ArgumentParser().parse(args, Arguments.MARKET_DATA_FILE_PATH));
        amount = new BigDecimal(new ArgumentParser().parse(args, Arguments.LOAN_AMOUNT));
    }

    def 'Loan service should respond with a loan validating the input' () {
        expect: 'the rate calculator should provide a loan'
        LoanService loanProvider = new LoanService()
        Loan loan = loanProvider.provide(args)
        loan != null

        and: 'the loan can be printed'
        loan.toString()
    }

}
