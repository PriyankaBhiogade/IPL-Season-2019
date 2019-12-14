package com.ipl2019;

import opencsvbuilder.CSVBuilderException;
import opencsvbuilder.CSVBuilderFactory;
import opencsvbuilder.ICSVBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.stream.StreamSupport;

public class CricketLeagueAnalyser {
    public int loadILPData(String csvFilePath) throws CricketLeagueAnalyserException {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<IPLRunsCSV> csvFileIterator = csvBuilder.getCsvFileIterator(reader, IPLRunsCSV.class);
            Iterable<IPLRunsCSV> csvIterable = () -> csvFileIterator;
            return (int) StreamSupport.stream(csvIterable.spliterator(), false)
                    .count();
        } catch (IOException e) {
            throw new CricketLeagueAnalyserException(e.getMessage(),
                    CricketLeagueAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (CSVBuilderException e) {
            throw new CricketLeagueAnalyserException(e.getMessage(),
                    CricketLeagueAnalyserException.ExceptionType.ERROR_FROM_CSV_BUILDER);
        } catch (RuntimeException e) {
            throw new CricketLeagueAnalyserException(e.getMessage(),
                    CricketLeagueAnalyserException.ExceptionType.SOME_ISSUE_IN_FILE);
        }
    }
}
