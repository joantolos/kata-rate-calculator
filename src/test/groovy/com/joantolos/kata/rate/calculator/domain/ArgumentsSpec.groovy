package com.joantolos.kata.rate.calculator.domain

import spock.lang.Specification

class ArgumentsSpec extends Specification {

    def 'First argument should be the market data file path' () {
        expect:
        Arguments.values()[0] == Arguments.MARKET_DATA_FILE_PATH
    }

    def 'Second argument should be the loan amount' () {
        expect:
        Arguments.values()[1] == Arguments.LOAN_AMOUNT
    }

    def 'Arguments should' () {
        given: 'a list of arguments detailing the data file location and loan amount'
        String[] args = ["/myFolder/myData.csv", "1000"]

        expect: 'retrieve the data file path'
        Arguments.parse(args, Arguments.MARKET_DATA_FILE_PATH) == "/myFolder/myData.csv"

        and: 'retrieve the loan amount'
        Arguments.parse(args, Arguments.LOAN_AMOUNT) == "1000"
    }

    def 'Arguments should raise Wrong Argument Exception' () {
        when: 'asking for loan without amount provided'
        String[] args = ["/myFolder/myData.csv"]
        Arguments.parse(args, Arguments.LOAN_AMOUNT)

        then: 'a Wrong Argument Exception is thrown'
        thrown(IllegalArgumentException)
    }

    def 'Arguments should not accept null value' () {
        when: 'asking without arguments'
        Arguments.parse(null, null)

        then: 'a Wrong Argument Exception is thrown'
        thrown(IllegalArgumentException)
    }

}
