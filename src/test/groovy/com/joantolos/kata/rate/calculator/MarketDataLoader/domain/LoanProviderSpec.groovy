package com.joantolos.kata.rate.calculator.MarketDataLoader.domain

import com.joantolos.kata.rate.calculator.domain.LoanProvider
import com.joantolos.kata.rate.calculator.domain.entity.Loan
import com.joantolos.kata.rate.calculator.exception.NotSufficientFoundsException
import spock.lang.Specification

class LoanProviderSpec extends Specification {

    private final String fakeDataPath = new File(this.getClass().getResource('/mockedMarketData.csv').toURI()).getAbsolutePath()

    def 'Loan provider should respond with a loan' () {
        given: 'a fake file located on resources and a loan request of 1000'
        String[] args = [fakeDataPath, "1000"]

        expect: 'the rate calculator should provide a loan'
        LoanProvider rateCalculator = new LoanProvider(args)
        Loan loan = rateCalculator.provide()
        loan != null

        and: 'the loan can be printed'
        loan.toString()
    }

    def 'Loan provider should tell when there are no sufficient founds' () {
        given: 'a fake file located on resources and a loan request of 10000000'
        String[] args = [fakeDataPath, "10000000"]

        when: 'the rate calculator should provide a loan'
        new LoanProvider(args).provide()

        then: 'a Not Sufficient Founds Exception is thrown'
        thrown(NotSufficientFoundsException)
    }

}
