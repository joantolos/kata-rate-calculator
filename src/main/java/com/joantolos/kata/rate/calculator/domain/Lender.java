package com.joantolos.kata.rate.calculator.domain;

import java.math.BigDecimal;

public class Lender {

    private String lender;
    private BigDecimal rate;
    private BigDecimal available;

    public Lender(String lender, BigDecimal rate, BigDecimal available) {
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

    public BigDecimal lend(BigDecimal toBorrow) {
        if(toBorrow.compareTo(this.available) > 0) {
            toBorrow = available;
        }
        return toBorrow.multiply(this.rate).add(toBorrow).setScale(2, BigDecimal.ROUND_FLOOR);
    }
}
