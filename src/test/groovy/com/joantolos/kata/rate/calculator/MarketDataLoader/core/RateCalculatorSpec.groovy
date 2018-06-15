package com.joantolos.kata.rate.calculator.MarketDataLoader.core

import com.joantolos.kata.rate.calculator.core.RateCalculator
import com.joantolos.kata.rate.calculator.domain.Loan
import spock.lang.Specification

class RateCalculatorSpec extends Specification {

    private final String fakeDataPath = new File(this.getClass().getResource('/mockedMarketData.csv').toURI()).getAbsolutePath()

    def 'Rate calculator should respond with a loan' () {
        given: 'a fake file located on resources and a loan request of 1000'
        String[] args = [fakeDataPath, "1000"]

        expect: 'the rate calculator should provide a loan'
        RateCalculator rateCalculator = new RateCalculator(args)
        Loan loan = rateCalculator.getLoan()
        loan != null

        and: 'the loan can be printed'
        loan.toString()

    }

}
