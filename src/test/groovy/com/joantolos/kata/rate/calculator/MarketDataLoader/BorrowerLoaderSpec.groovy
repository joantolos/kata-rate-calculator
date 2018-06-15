package com.joantolos.kata.rate.calculator.MarketDataLoader

import com.joantolos.kata.rate.calculator.BorrowerLoader
import com.joantolos.kata.rate.calculator.exception.MarketDataFileLoadingException
import spock.lang.Shared
import spock.lang.Specification

class BorrowerLoaderSpec extends Specification {

    private final String fakeDataPath = new File(this.getClass().getResource('/mockedMarketData.csv').toURI()).getAbsolutePath()
    @Shared BorrowerLoader loader

    def setupSpec() {
        loader = new BorrowerLoader()
    }

    def 'Borrower loader should load data from test resources' () {
        given: 'a fake file located on resources'
        def borrowers = loader.load(fakeDataPath)

        expect: 'the borrowers list to be completed'
        borrowers != null
        borrowers.size() == 7
        borrowers.get(0).lender == "Bob"
    }

    def 'Borrower loader should raise exception when facing wrong file path' () {
        when: 'a non existing file'
        loader.load('noFile.csv')

        then: 'a Market Data File Loading Exception is thrown'
        thrown(MarketDataFileLoadingException)
    }
}
