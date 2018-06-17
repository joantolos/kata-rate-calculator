package com.joantolos.kata.rate.calculator.domain

import spock.lang.Specification

class BorrowerSpec extends Specification {

    def 'should be able to provide the final amount borrowed plus the interest' () {
        given: 'a amount fully available by the borrower'
        Borrower borrower = new Borrower("Joan", new BigDecimal(0.07), new BigDecimal(1500))

        expect:
        borrower.borrow(1000) == new BigDecimal(1070)
    }

    def 'should be able to provide the final amount borrowed plus the interest with a larger amount' () {
        given: 'a amount not fully available by the borrower'
        Borrower borrower = new Borrower("Joan", new BigDecimal(0.07), new BigDecimal(1500))

        expect:
        borrower.borrow(2000) == new BigDecimal(1605)
    }

}
