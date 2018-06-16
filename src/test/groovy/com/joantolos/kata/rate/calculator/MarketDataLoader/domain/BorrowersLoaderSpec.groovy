package com.joantolos.kata.rate.calculator.MarketDataLoader.domain

import com.joantolos.kata.rate.calculator.domain.BorrowersLoader
import com.joantolos.kata.rate.calculator.exception.MarketDataFileLoadingException
import spock.lang.Shared
import spock.lang.Specification

class BorrowersLoaderSpec extends Specification {

    private final String fakeDataPath = new File(this.getClass().getResource('/mockedMarketData.csv').toURI()).getAbsolutePath()

    @Shared BorrowersLoader borrowerLoader

    def setupSpec() {
        borrowerLoader = new BorrowersLoader()
    }

    def 'Borrowers loader should load data from test resources' () {
        given: 'a fake file located on resources'
        def borrowers = borrowerLoader.load(fakeDataPath)

        expect: 'the borrowers list to be completed'
        borrowers != null
        borrowers.size() == 7
        borrowers.get(4).lender == "Bob"
        borrowers.get(4).rate == 0.075
        borrowers.get(4).available == 640
    }

    def 'Borrowers loader should raise exception when facing wrong file path' () {
        when: 'a non existing file'
        borrowerLoader.load('noFile.csv')

        then: 'a Market Data File Loading Exception is thrown'
        thrown(MarketDataFileLoadingException)
    }
    
    def 'Borrowers loader should retrieve borrowers sorted by rate ascending' () {
        given: 'a fake file located on resources'
        def borrowers = borrowerLoader.load(fakeDataPath)

        expect: 'the borrowers list to be sorted by rate ascendant'
        borrowers.get(0).lender == "Jane"
        borrowers.get(1).lender == "Fred"
        borrowers.get(2).lender == "Angela"
        borrowers.get(3).lender == "Dave"
        borrowers.get(4).lender == "Bob"
        borrowers.get(5).lender == "John"
        borrowers.get(6).lender == "Mary"
    }
}
