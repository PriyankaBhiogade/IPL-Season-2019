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

    Map<CricketAnalyserENUM.StatisticFields, Comparator<IPLRunsDAO>> sortBy = null;
    Map<String, IPLRunsDAO> iplRunMap = null;

    public CricketLeagueAnalyser() {
        this.sortBy = new HashMap<>();
        this.iplRunMap = new HashMap<>();
        this.sortBy.put(CricketAnalyserENUM.StatisticFields.AVERAGE, Comparator.comparing(data -> data.battingAvg, Comparator.reverseOrder()));
        this.sortBy.put(CricketAnalyserENUM.StatisticFields.STRIKING_RATE, Comparator.comparing(data -> data.strikeRate, Comparator.reverseOrder()));
        this.sortBy.put(CricketAnalyserENUM.StatisticFields.MAX_SIX_AND_FOUR, new ComparingFields().reversed());
        this.sortBy.put(CricketAnalyserENUM.StatisticFields.BEST_STRIKING_RATE_WITH_SIX_AND_FOUR, new ComparingFields().reversed().thenComparing(data -> data.strikeRate));
        Comparator<IPLRunsDAO> comp = Comparator.comparing(censusDAO -> censusDAO.battingAvg, Comparator.reverseOrder());
        this.sortBy.put(CricketAnalyserENUM.StatisticFields.BEST_AVG_WITH_BEST_STRIKING, comp.thenComparing(data -> data.strikeRate, Comparator.reverseOrder()));
        Comparator<IPLRunsDAO> comp1 = Comparator.comparing(censusDAO -> censusDAO.run, Comparator.reverseOrder());
        this.sortBy.put(CricketAnalyserENUM.StatisticFields.MAX_RUN_WITH_BEST_AVG, comp1.thenComparing(data -> data.battingAvg, Comparator.reverseOrder()));
    }

    public int loadIPLRunsData(String csvFilePath) throws CricketLeagueAnalyserException {
        iplRunMap = this.loadData(csvFilePath, IPLRunsCSV.class);
        return iplRunMap.size();
    }

    public int loadILPWiktsData(String csvFilePath) throws CricketLeagueAnalyserException {
        iplRunMap = this.loadData(csvFilePath, IPLWiktsCSV.class);
        return iplRunMap.size();
    }

    private <E> Map<String, IPLRunsDAO> loadData(String csvFilePath, Class<E> iplCSVClass) throws CricketLeagueAnalyserException {
        String updatedFile = this.prepareFileData(csvFilePath);
        try (Reader reader = Files.newBufferedReader(Paths.get(updatedFile))) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<E> csvFileIterator = csvBuilder.getCsvFileIterator(reader, iplCSVClass);
            Iterable<E> csvIterable = () -> csvFileIterator;
            if (iplCSVClass.getName().equals("com.ipl2019.IPLRunsCSV")) {
                StreamSupport.stream(csvIterable.spliterator(), false)
                        .map(IPLRunsCSV.class::cast)
                        .forEach(iplCSV -> iplRunMap.put(iplCSV.player, new IPLRunsDAO(iplCSV)));
            } else if (iplCSVClass.getName().equals("com.ipl2019.IPLWiktsCSV")) {
                StreamSupport.stream(csvIterable.spliterator(), false)
                        .map(IPLWiktsCSV.class::cast)
                        .forEach(iplCSV -> iplRunMap.put(iplCSV.player, new IPLRunsDAO(iplCSV)));
            }
            return iplRunMap;
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
        String IPL_RUNS_CSV_FILE_PATH = "./src/test/resources/NewIPL2019FactsheetMostRuns.csv";
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

    public String getSortData(CricketAnalyserENUM.StatisticFields field) throws CricketLeagueAnalyserException {
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
