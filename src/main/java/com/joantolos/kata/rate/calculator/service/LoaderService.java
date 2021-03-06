package com.joantolos.kata.rate.calculator.service;

import com.joantolos.kata.rate.calculator.domain.Lender;
import com.joantolos.kata.rate.calculator.exception.MarketDataFileLoadingException;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class LoaderService {

    private final String SEPARATOR = ",";

    public List<Lender> load(String filePath) throws MarketDataFileLoadingException {
        try {
            return Files.lines(Paths.get(filePath))
                    .skip(1)
                    .map(line -> {
                        String[] lineFields = line.split(SEPARATOR);
                        return new Lender(
                                lineFields[0],
                                new BigDecimal(lineFields[1]),
                                new BigDecimal(lineFields[2]));
                    })
                    .sorted(Comparator.comparing(Lender::getRate))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new MarketDataFileLoadingException();
        }
    }

}
