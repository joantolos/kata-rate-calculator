package com.joantolos.kata.rate.calculator.service

import com.joantolos.kata.rate.calculator.exception.MarketDataFileLoadingException
import spock.lang.Shared
import spock.lang.Specification

class LoaderServiceSpec extends Specification {

    private final String fakeDataPath = new File(this.getClass().getResource('/mockedMarketData.csv').toURI()).getAbsolutePath()

    @Shared LoaderService loaderService

    def setupSpec() {
        loaderService = new LoaderService()
    }

    def 'Lenders loader should load data from test resources' () {
        given: 'a fake file located on resources'
        def lenders = loaderService.load(fakeDataPath)

        expect: 'the lenders list to be completed'
        lenders != null
        lenders.size() == 7
        lenders.get(4).lender == "Bob"
        lenders.get(4).rate == 0.075
        lenders.get(4).available == 640
    }

    def 'Lenders loader should raise exception when facing wrong file path' () {
        when: 'a non existing file'
        loaderService.load('noFile.csv')

        then: 'a Market Data File Loading Exception is thrown'
        thrown(MarketDataFileLoadingException)
    }
    
    def 'Lenders loader should retrieve lenders sorted by rate ascending' () {
        given: 'a fake file located on resources'
        def lenders = loaderService.load(fakeDataPath)

        expect: 'the lenders list to be sorted by rate ascendant'
        lenders.get(0).lender == "Jane"
        lenders.get(1).lender == "Fred"
        lenders.get(2).lender == "Angela"
        lenders.get(3).lender == "Dave"
        lenders.get(4).lender == "Bob"
        lenders.get(5).lender == "John"
        lenders.get(6).lender == "Mary"
    }
}
