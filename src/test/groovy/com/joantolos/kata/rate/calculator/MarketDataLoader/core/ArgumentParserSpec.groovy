package com.joantolos.kata.rate.calculator.MarketDataLoader.core

import com.joantolos.kata.rate.calculator.domain.ArgumentParser
import com.joantolos.kata.rate.calculator.domain.entity.Arguments
import com.joantolos.kata.rate.calculator.exception.WrongArgumentsException
import spock.lang.Shared
import spock.lang.Specification

class ArgumentParserSpec extends Specification {

    @Shared ArgumentParser argument

    def setupSpec() {
        argument = new ArgumentParser()
    }

    def 'Argument should' () {
        given: 'a list of arguments detailing the data file location and loan amount'
        String[] args = ["/myFolder/myData.csv", "1000"]

        expect: 'retrieve the data file path'
        argument.parse(args, Arguments.MARKET_DATA_FILE_PATH) == "/myFolder/myData.csv"

        and: 'retrieve the loan amount'
        argument.parse(args, Arguments.LOAN_AMOUNT) == "1000"
    }

    def 'Argument should raise Wrong Argument Exception' () {
        when: 'asking for loan amount'
        String[] args = ["/myFolder/myData.csv"]
        argument.parse(args, Arguments.LOAN_AMOUNT)

        then: 'a Wrong Argument Exception is thrown'
        thrown(WrongArgumentsException)
    }

    def 'Argument should not accept null value' () {
        when: 'asking for loan amount'
        argument.parse(null, null)

        then: 'a Wrong Argument Exception is thrown'
        thrown(WrongArgumentsException)
    }

}
