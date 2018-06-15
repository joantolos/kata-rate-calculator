package com.joantolos.kata.rate.calculator.domain.entity;

public class Loan {

    private String requestedAmount;
    private String rate;
    private String monthlyRepayment;
    private String totalRepayment;

    public Loan(String requestedAmount,
                String rate,
                String monthlyRepayment,
                String totalRepayment) {
        this.requestedAmount = requestedAmount;
        this.rate = rate;
        this.monthlyRepayment = monthlyRepayment;
        this.totalRepayment = totalRepayment;
    }

    @Override
    public String toString() {
        return "Requested amount: £" + this.requestedAmount + "\n" +
                "Rate: " + this.rate + "\n" +
                "Monthly repayment: £" + this.monthlyRepayment + "\n" +
                "Total repayment: £" + this.totalRepayment;
    }
}
