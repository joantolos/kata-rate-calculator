package com.joantolos.kata.rate.calculator.domain

import spock.lang.Specification

class LenderSpec extends Specification {

    def 'should be able to provide the final amount borrowed plus the interest' () {
        given: 'a amount fully available by the borrower'
        Lender borrower = new Lender("Joan", 0.07, new BigDecimal(1500))

        expect:
        borrower.lend(new BigDecimal(1000)) == new BigDecimal(1070)
    }

    def 'should be able to provide the final amount borrowed plus the interest with a larger amount' () {
        given: 'a amount not fully available by the borrower'
        Lender borrower = new Lender("Joan", 0.07, new BigDecimal(1500))

        expect:
        borrower.lend(new BigDecimal(2000)) == new BigDecimal(1605)
    }

}
