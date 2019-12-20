package com.ipl2019;

import opencsvbuilder.CSVBuilderException;
import opencsvbuilder.CSVBuilderFactory;
import opencsvbuilder.ICSVBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public abstract class IPLLoaderAdapter {
    public abstract Map<String, IPLDAO> loadData(String csvFilePath) throws CricketLeagueAnalyserException;

    protected <E> Map<String, IPLDAO> loadData(Class<E> iplCSVClass, String csvFilePath) throws CricketLeagueAnalyserException {
        Map<String, IPLDAO> iplMap = new HashMap<>();
        String updatedFile = this.prepareFileData(csvFilePath);
        try (Reader reader = Files.newBufferedReader(Paths.get(updatedFile))) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<E> csvFileIterator = csvBuilder.getCsvFileIterator(reader, iplCSVClass);
            Iterable<E> csvIterable = () -> csvFileIterator;
            if (iplCSVClass.getName().equals("com.ipl2019.IPLBatsmanCSV")) {
                StreamSupport.stream(csvIterable.spliterator(), false)
                        .map(IPLBatsmanCSV.class::cast)
                        .forEach(iplCSV -> iplMap.put(iplCSV.player, new IPLDAO(iplCSV)));
            } else if (iplCSVClass.getName().equals("com.ipl2019.IPLBowlerCSV")) {
                StreamSupport.stream(csvIterable.spliterator(), false)
                        .map(IPLBowlerCSV.class::cast)
                        .forEach(iplCSV -> iplMap.put(iplCSV.player, new IPLDAO(iplCSV)));
            }
            return iplMap;
        } catch (IOException e) {
            throw new CricketLeagueAnalyserException(e.getMessage(),
                    CricketLeagueAnalyserException.ExceptionType.NO_SUCH_FILE);
        } catch (CSVBuilderException e) {
            throw new CricketLeagueAnalyserException(e.getMessage(),
                    CricketLeagueAnalyserException.ExceptionType.ERROR_FROM_CSV_BUILDER);
        } catch (RuntimeException e) {
            throw new CricketLeagueAnalyserException(e.getMessage(),
                    CricketLeagueAnalyserException.ExceptionType.SOME_ISSUE_IN_FILE);
        }
    }

    public String prepareFileData(String filePath) throws CricketLeagueAnalyserException {
        String searchFor = "-";
        String replaceWith = "0.0";
        String IPL_RUNS_CSV_FILE_PATH = "./src/test/resources/NewIPL2019Factsheet.csv";
        try (Stream<String> lines = Files.lines(Paths.get(filePath))) {
            List<String> replaced = lines
                    .map(line -> line.replaceAll(searchFor, replaceWith))
                    .collect(Collectors.toList());
            Files.write(Paths.get(IPL_RUNS_CSV_FILE_PATH), replaced);
            return IPL_RUNS_CSV_FILE_PATH;
        } catch (IOException e) {
            throw new CricketLeagueAnalyserException(e.getMessage(),
                    CricketLeagueAnalyserException.ExceptionType.NO_SUCH_FILE);
        }
    }
}
