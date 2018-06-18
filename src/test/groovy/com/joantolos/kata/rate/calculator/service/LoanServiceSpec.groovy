package com.joantolos.kata.rate.calculator.service

import com.joantolos.kata.rate.calculator.ArgumentParser
import com.joantolos.kata.rate.calculator.LendersLoader
import com.joantolos.kata.rate.calculator.domain.Arguments
import com.joantolos.kata.rate.calculator.domain.Lender
import com.joantolos.kata.rate.calculator.domain.Loan
import spock.lang.Shared
import spock.lang.Specification

class LoanServiceSpec extends Specification {

    @Shared String fakeDataPath = new File(this.getClass().getResource('/mockedMarketData.csv').toURI()).getAbsolutePath()
    @Shared String[] args
    @Shared List<Lender> borrowers
    @Shared BigDecimal amount

    def setupSpec() {
        args = [fakeDataPath, "1000"]
        borrowers = new LendersLoader().load(new ArgumentParser().parse(args, Arguments.MARKET_DATA_FILE_PATH));
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

    def 'Loan service should respond with the correct loan for a 1000 pounds amount' () {
        expect:
        LoanService loanProvider = new LoanService()
        Loan loan = loanProvider.provide(args)
        loan.toString() ==
                "Requested amount: £1000\n" +
                "Rate: 0.07\n" +
                "Monthly repayment: £34.16\n" +
                "Total repayment: £1230.00"
    }

    def 'Loan service should respond with the correct loan for a 2000 pounds amount' () {
        expect:
        LoanService loanProvider = new LoanService()
        String [] args = [fakeDataPath, "2000"]
        Loan loan = loanProvider.provide(args)
        loan.toString() ==
                "Requested amount: £2000\n" +
                "Rate: 0.07\n" +
                "Monthly repayment: £68.33\n" +
                "Total repayment: £2460.00"
    }

}
