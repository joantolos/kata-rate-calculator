package com.joantolos.kata.rate.calculator

import com.joantolos.kata.rate.calculator.domain.Arguments
import com.joantolos.kata.rate.calculator.exception.WrongArgumentsException
import spock.lang.Shared
import spock.lang.Specification

class ArgumentParserSpec extends Specification {

    @Shared ArgumentParser argument

    def setupSpec() {
        argument = new ArgumentParser()
    }

    def 'Argument parser should' () {
        given: 'a list of arguments detailing the data file location and loan amount'
        String[] args = ["/myFolder/myData.csv", "1000"]

        expect: 'retrieve the data file path'
        argument.parse(args, Arguments.MARKET_DATA_FILE_PATH) == "/myFolder/myData.csv"

        and: 'retrieve the loan amount'
        argument.parse(args, Arguments.LOAN_AMOUNT) == "1000"
    }

    def 'Argument parser should raise Wrong Argument Exception' () {
        when: 'asking for loan without amount provided'
        String[] args = ["/myFolder/myData.csv"]
        argument.parse(args, Arguments.LOAN_AMOUNT)

        then: 'a Wrong Argument Exception is thrown'
        thrown(WrongArgumentsException)
    }

    def 'Argument parser should not accept null value' () {
        when: 'asking without arguments'
        argument.parse(null, null)

        then: 'a Wrong Argument Exception is thrown'
        thrown(WrongArgumentsException)
    }

}
