package com.joantolos.kata.rate.calculator.domain.entity;

import java.math.BigDecimal;

public class Loan {

    private BigDecimal requestedAmount;
    private BigDecimal rate;
    private BigDecimal monthlyRepayment;
    private BigDecimal totalRepayment;

    public Loan(BigDecimal requestedAmount,
                BigDecimal rate,
                BigDecimal monthlyRepayment,
                BigDecimal totalRepayment) {
        this.requestedAmount = requestedAmount;
        this.rate = rate;
        this.monthlyRepayment = monthlyRepayment;
        this.totalRepayment = totalRepayment;
    }

    @Override
    public String toString() {
        return "Requested amount: £" + this.requestedAmount.toString() + "\n" +
                "Rate: " + this.rate.toString() + "\n" +
                "Monthly repayment: £" + this.monthlyRepayment.toString() + "\n" +
                "Total repayment: £" + this.totalRepayment.toString();
    }
}
