package com.joantolos.kata.rate.calculator.core;

import com.joantolos.kata.rate.calculator.domain.Borrower;
import com.joantolos.kata.rate.calculator.exception.MarketDataFileLoadingException;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class BorrowerLoader {

    public List<Borrower> load(String filePath){
        try {
            return Files.lines(Paths.get(filePath))
                    .skip(1) //File header out
                    .map(line -> {
                        String[] lineFields = line.split(",");
                        return new Borrower(
                                lineFields[0],
                                new BigDecimal(lineFields[1]),
                                new BigDecimal(lineFields[2]));
                    })
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new MarketDataFileLoadingException(e.getMessage());
        }
    }
}
