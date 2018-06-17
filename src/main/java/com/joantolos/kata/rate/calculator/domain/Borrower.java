package com.joantolos.kata.rate.calculator.domain;

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

    public BigDecimal borrow(BigDecimal toBorrow) {
        if(toBorrow.compareTo(this.available) == 1) {
            toBorrow = available;
        }
        return toBorrow.multiply(this.rate).add(toBorrow).setScale(2, BigDecimal.ROUND_FLOOR);
    }
}
