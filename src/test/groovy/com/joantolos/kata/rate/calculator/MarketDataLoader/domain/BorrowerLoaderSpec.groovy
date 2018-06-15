package com.joantolos.kata.rate.calculator.MarketDataLoader.domain

import com.joantolos.kata.rate.calculator.domain.BorrowerLoader
import com.joantolos.kata.rate.calculator.exception.MarketDataFileLoadingException
import spock.lang.Shared
import spock.lang.Specification

class BorrowerLoaderSpec extends Specification {

    private final String fakeDataPath = new File(this.getClass().getResource('/mockedMarketData.csv').toURI()).getAbsolutePath()

    @Shared BorrowerLoader borrowerLoader

    def setupSpec() {
        borrowerLoader = new BorrowerLoader()
    }

    def 'Borrower loader should load data from test resources' () {
        given: 'a fake file located on resources'
        def borrowers = borrowerLoader.load(fakeDataPath)

        expect: 'the borrowers list to be completed'
        borrowers != null
        borrowers.size() == 7
        borrowers.get(0).lender == "Bob"
        borrowers.get(0).rate == 0.075
        borrowers.get(0).available == 640
    }

    def 'Borrower loader should raise exception when facing wrong file path' () {
        when: 'a non existing file'
        borrowerLoader.load('noFile.csv')

        then: 'a Market Data File Loading Exception is thrown'
        thrown(MarketDataFileLoadingException)
    }
}
