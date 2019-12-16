package com.ipl2019;

import com.google.gson.Gson;
import opencsvbuilder.CSVBuilderException;
import opencsvbuilder.CSVBuilderFactory;
import opencsvbuilder.ICSVBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toCollection;

public class CricketLeagueAnalyser {

    Map<CricketAnalyserENUM.StatesticFields, Comparator<IPLRunsDAO>> sortBy = null;
    Map<String, IPLRunsDAO> iplRunMap = null;

    public CricketLeagueAnalyser() {
        this.sortBy = new HashMap<>();
        this.sortBy.put(CricketAnalyserENUM.StatesticFields.AVERAGE, Comparator.comparing(data -> data.battingAvg, Comparator.reverseOrder()));
        this.sortBy.put(CricketAnalyserENUM.StatesticFields.STRIKING_RATE, Comparator.comparing(census -> census.strikeRate, Comparator.reverseOrder()));
        this.sortBy.put(CricketAnalyserENUM.StatesticFields.AVERAGE, Comparator.comparing(data -> data.battingAvg, Comparator.reverseOrder()));

    }

    public Map<String, IPLRunsDAO> loadILPData(String csvFilePath) throws CricketLeagueAnalyserException {
        iplRunMap = new HashMap<>();
        try {
            Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<IPLRunsCSV> csvFileIterator = csvBuilder.getCsvFileIterator(reader, IPLRunsCSV.class);
            Iterable<IPLRunsCSV> csvIterable = () -> csvFileIterator;
            StreamSupport.stream(csvIterable.spliterator(), false)
                    .map(IPLRunsCSV.class::cast)
                    .forEach(runCSV -> iplRunMap.put(runCSV.player, new IPLRunsDAO(runCSV)));
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
        return iplRunMap;
    }

    public void prepareFileData(String filePath) {
        String searchFor = "-";
        String replaceWith = "0";
        String IPL_RUNS_CSV_FILE_PATH = "./src/test/resources/NewIPL2019FactsheetMostRuns.csv";
        try (Stream<String> lines = Files.lines(Paths.get(filePath))) {
            List<String> replaced = lines
                    .map(line -> line.replaceAll(searchFor, replaceWith))
                    .collect(Collectors.toList());
            Files.write(Paths.get(IPL_RUNS_CSV_FILE_PATH), replaced);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getSortData(CricketAnalyserENUM.StatesticFields field) throws CricketLeagueAnalyserException {
        if (iplRunMap == null || iplRunMap.size() == 0) {
            throw new CricketLeagueAnalyserException("No Census Data",
                    CricketLeagueAnalyserException.ExceptionType.DATA_NOT_FOUND);
        }
        ArrayList recordList = iplRunMap.values().stream()
                .sorted(sortBy.get(field))
                .collect(toCollection(ArrayList::new));
        String sortedStateCensusJson = new Gson().toJson(recordList);
        return sortedStateCensusJson;
    }
}
