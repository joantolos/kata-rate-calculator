package com.joantolos.kata.rate.calculator.domain

import spock.lang.Specification

class LenderSpec extends Specification {

    def 'should be able to provide the final amount borrowed plus the interest' () {
        given: 'a amount fully available by the borrower'
        Lender borrower = new Lender("Joan", new BigDecimal(0.07), new BigDecimal(1500))

        expect:
        borrower.lend(1000) == new BigDecimal(1070)
    }

    def 'should be able to provide the final amount borrowed plus the interest with a larger amount' () {
        given: 'a amount not fully available by the borrower'
        Lender borrower = new Lender("Joan", new BigDecimal(0.07), new BigDecimal(1500))

        expect:
        borrower.lend(2000) == new BigDecimal(1605)
    }

}
