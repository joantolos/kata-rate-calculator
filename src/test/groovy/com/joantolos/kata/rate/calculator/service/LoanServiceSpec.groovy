package com.joantolos.kata.rate.calculator.service

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
        borrowers = new LoaderService().load(Arguments.parse(args, Arguments.MARKET_DATA_FILE_PATH));
        amount = new BigDecimal(Arguments.parse(args, Arguments.LOAN_AMOUNT));
    }

    def 'Loan service should respond with a loan validating the input' () {
        expect: 'the rate calculator should provide a loan'
        LoanService loanProvider = new LoanService()
        Loan loan = loanProvider.provide(args)
        loan != null

        and: 'the loan can be printed'
        loan.toString() != null
    }

    def 'Loan service should respond with the correct loan for a 1000 pounds amount' () {
        expect:
        LoanService loanProvider = new LoanService()
        Loan loan = loanProvider.provide(args)
        loan.toString() ==
                "Requested amount: £1000\n" +
                "Rate: 7.0%\n" +
                "Monthly repayment: £29.72\n" +
                "Total repayment: £1070.04"
    }

    def 'Loan service should respond with the correct loan for a 2000 pounds amount' () {
        expect:
        LoanService loanProvider = new LoanService()
        String [] args = [fakeDataPath, "2000"]
        Loan loan = loanProvider.provide(args)
        loan.toString() ==
                "Requested amount: £2000\n" +
                "Rate: 14.0%\n" +
                "Monthly repayment: £64.40\n" +
                "Total repayment: £2318.58"
    }

}
