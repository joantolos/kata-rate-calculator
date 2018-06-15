package com.joantolos.kata.rate.calculator.MarketDataLoader.core

import com.joantolos.kata.rate.calculator.core.Argument
import com.joantolos.kata.rate.calculator.core.Arguments
import com.joantolos.kata.rate.calculator.exception.WrongArgumentsException
import spock.lang.Shared
import spock.lang.Specification

class ArgumentSpec extends Specification {

    @Shared Argument argument

    def setupSpec() {
        argument = new Argument()
    }

    def 'Argument should' () {
        given: 'a list of arguments detailing the data file location and loan amount'
        String[] args = ["/myFolder/myData.csv", "1000"]

        expect: 'retrieve the data file path'
        argument.get(args, Arguments.MARKET_DATA_FILE_PATH) == "/myFolder/myData.csv"

        and: 'retrieve the loan amount'
        argument.get(args, Arguments.LOAN_AMOUNT) == "1000"
    }

    def 'Argument should raise Wrong Argument Exception' () {
        when: 'asking for loan amount'
        String[] args = ["/myFolder/myData.csv"]
        argument.get(args, Arguments.LOAN_AMOUNT)

        then: 'a Wrong Argument Exception is thrown'
        thrown(WrongArgumentsException)
    }

}
