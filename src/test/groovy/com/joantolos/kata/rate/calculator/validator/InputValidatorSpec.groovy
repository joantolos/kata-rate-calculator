package com.joantolos.kata.rate.calculator.validator

import com.joantolos.kata.rate.calculator.domain.Arguments
import com.joantolos.kata.rate.calculator.domain.Lender
import com.joantolos.kata.rate.calculator.exception.IncorrectAmountException
import com.joantolos.kata.rate.calculator.exception.NotSufficientFoundsException
import com.joantolos.kata.rate.calculator.service.LoaderService
import spock.lang.Shared
import spock.lang.Specification

class InputValidatorSpec extends Specification {

    @Shared InputValidator inputValidator
    @Shared List<Lender> borrowers
    @Shared BigDecimal amount

    private final String fakeDataPath = new File(this.getClass().getResource('/mockedMarketData.csv').toURI()).getAbsolutePath()

    def setupSpec() {
        inputValidator = new InputValidator(1000, 15000, 100)
    }

    def 'Input validator should raise an exception when wrong number of arguments passed' () {
        given: 'a list of arguments detailing the data file location alone'
        String[] args = ["/myFolder/myData.csv"]

        when: 'validating the input'
        inputValidator.validateArgs(args)

        then: 'a Wrong Argument Exception is thrown'
        thrown(IllegalArgumentException)
    }

    def 'Input validator should raise an exception when the loan amount is higher than total available' () {
        given: 'a list of arguments detailing the data file location and a big loan amount'
        String[] args = [fakeDataPath,"15000"]
        borrowers = new LoaderService().load(Arguments.parse(args, Arguments.MARKET_DATA_FILE_PATH));
        amount = new BigDecimal(Arguments.parse(args, Arguments.LOAN_AMOUNT));

        when: 'validating the input'
        inputValidator.validateLoanAmount(borrowers, amount)

        then: 'a Not Sufficient Founds Exception is thrown'
        thrown(NotSufficientFoundsException)
    }

    def 'Input validator should raise an exception when the loan amount is not correct' () {
        given: 'a list of arguments detailing the data file location and an invalid loan amount'
        String[] args = [fakeDataPath,"1005"]
        borrowers = new LoaderService().load(Arguments.parse(args, Arguments.MARKET_DATA_FILE_PATH));
        amount = new BigDecimal(Arguments.parse(args, Arguments.LOAN_AMOUNT));

        when: 'validating the input'
        inputValidator.validateLoanAmount(borrowers, amount)

        then: 'an Incorrect Amount Exception is thrown'
        thrown(IncorrectAmountException)
    }

}
