package com.joantolos.kata.rate.calculator.domain.entity;

import java.math.BigDecimal;

public class Borrower {

    private String lender;
    private BigDecimal rate;
    private BigDecimal available;

    public Borrower(String lender, BigDecimal rate, BigDecimal available) {
        this.lender = lender;
        this.rate = rate;
        this.available = available;
    }

    public String getLender() {
        return lender;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public BigDecimal getAvailable() {
        return available;
    }
}
