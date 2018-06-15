package com.joantolos.kata.rate.calculator.domain.entity;

public enum Arguments {
    MARKET_DATA_FILE_PATH(0),
    LOAN_AMOUNT(1);

    private Integer index;

    Arguments(Integer index) {
        this.index = index;
    }

    public Integer getIndex() {
        return index;
    }
}
