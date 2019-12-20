package com.ipl2019;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;

public class CricketLeagueAnalyserTest {

    private static final String IPL_RUNS_CSV_FILE_PATH = "./src/test/resources/IPL2019FactsheetMostRuns.csv";
    private static final String IPL_RUNS_CSV_FILE_PATH_WITH_WRONG_FILE = "IPL2019FactsheetMostRuns123.csv";
    private static final String IPL_RUNS_CSV_FILE_PATH_WITH_DELIMITER_ERR = "./src/test/resources/IPL2019FactsheetMostRunsWithDelimiterErr.csv";
    private static final String IPL_RUNS_CSV_FILE_PATH_WITH_HEADER_ERR = "./src/test/resources/IPL2019FactsheetMostRunsWithHeaderError.csv";
    private static final String EMPTY_FILE_PATH = "./src/test/resources/EmptyFile.csv";
    private static final String IPL_WKTS_CSV_FILE_PATH = "./src/test/resources/IPL2019FactsheetMostWkts.csv";

    @Test
    public void givenCricketLeagueRunsCsvFile_WhenFileCorrect_ShouldReturnTopBatsmanAvg() {
        try {
            CricketLeagueAnalyser iplAnalyser = new CricketLeagueAnalyser();
            iplAnalyser.loadIPLData(IPLPlayers.BATSMAN, IPL_RUNS_CSV_FILE_PATH);
            String sortedData = iplAnalyser.getSortData(SortingEnums.StatisticFields.BATTING_AVERAGE);
            IPLBatsmanCSV[] censusCSV = new Gson().fromJson(sortedData, IPLBatsmanCSV[].class);
            Assert.assertEquals("MS Dhoni", censusCSV[0].player);
        } catch (CricketLeagueAnalyserException e) {
        }
    }

    @Test
    public void givenCricketLeagueRunsCsvFile_WhenFileWrong_ShouldThrowingException() {
        CricketLeagueAnalyser iplAnalyser = new CricketLeagueAnalyser();
        try {
            iplAnalyser.loadIPLData(IPLPlayers.BATSMAN, IPL_RUNS_CSV_FILE_PATH);
            iplAnalyser.getSortData(SortingEnums.StatisticFields.BATTING_AVERAGE);
        } catch (CricketLeagueAnalyserException e) {
            Assert.assertEquals(CricketLeagueAnalyserException.ExceptionType.SOME_ISSUE_IN_FILE, e.type);
        }
    }

    @Test
    public void givenCricketLeagueRunsCsvFile_WhenFileNotLoaded_ShouldThrowingException() {
        try {
            CricketLeagueAnalyser iplAnalyser = new CricketLeagueAnalyser();
            iplAnalyser.getSortData(SortingEnums.StatisticFields.BATTING_AVERAGE);
        } catch (CricketLeagueAnalyserException e) {
            Assert.assertEquals(CricketLeagueAnalyserException.ExceptionType.DATA_NOT_FOUND, e.type);
        }
    }

    @Test
    public void givenCricketLeagueRunsCsvFile_WhenFileCorrect_ShouldReturnTopStrikingRate() {
        try {
            CricketLeagueAnalyser iplAnalyser = new CricketLeagueAnalyser();
            iplAnalyser.loadIPLData(IPLPlayers.BATSMAN, IPL_RUNS_CSV_FILE_PATH);
            String sortedData = iplAnalyser.getSortData(SortingEnums.StatisticFields.BATSMAN_STRIKING_RATE);
            IPLBatsmanCSV[] censusCSV = new Gson().fromJson(sortedData, IPLBatsmanCSV[].class);
            Assert.assertEquals("Ishant Sharma", censusCSV[0].player);
        } catch (CricketLeagueAnalyserException e) {
        }
    }

    @Test
    public void givenCricketLeagueRunsCsvFile_WhenFileIsWrong_ShouldThrowException() {
        CricketLeagueAnalyser iplAnalyser = new CricketLeagueAnalyser();
        try {
            iplAnalyser.loadIPLData(IPLPlayers.BATSMAN, EMPTY_FILE_PATH);
            iplAnalyser.getSortData(SortingEnums.StatisticFields.BATSMAN_STRIKING_RATE);
        } catch (CricketLeagueAnalyserException e) {
            Assert.assertEquals(CricketLeagueAnalyserException.ExceptionType.ERROR_FROM_CSV_BUILDER, e.type);
        }
    }

    @Test
    public void givenCricketLeagueRunsCsvFile_WhenFileNotLoaded_ShouldThrowException() {
        try {
            CricketLeagueAnalyser iplAnalyser = new CricketLeagueAnalyser();
            iplAnalyser.getSortData(SortingEnums.StatisticFields.BATSMAN_STRIKING_RATE);
        } catch (CricketLeagueAnalyserException e) {
            Assert.assertEquals(CricketLeagueAnalyserException.ExceptionType.DATA_NOT_FOUND, e.type);
        }
    }

    @Test
    public void givenCricketLeagueRunsCsvFile_SortBySixAndFour_WhenFileCorrect_ShouldReturnMaxSixthAndFours() {
        try {
            CricketLeagueAnalyser iplAnalyser = new CricketLeagueAnalyser();
            iplAnalyser.loadIPLData(IPLPlayers.BATSMAN, IPL_RUNS_CSV_FILE_PATH);
            String sortedData = iplAnalyser.getSortData(SortingEnums.StatisticFields.MAX_SIX_AND_FOUR);
            IPLBatsmanCSV[] censusCSV = new Gson().fromJson(sortedData, IPLBatsmanCSV[].class);
            Assert.assertEquals("Andre Russell", censusCSV[0].player);
        } catch (CricketLeagueAnalyserException e) {
        }
    }

    @Test
    public void givenCricketLeagueRunsCsvFile_SortBySixAndFour_WhenFileIsWrong_ShouldThrowingException() {
        try {
            CricketLeagueAnalyser iplAnalyser = new CricketLeagueAnalyser();
            iplAnalyser.loadIPLData(IPLPlayers.BATSMAN, IPL_RUNS_CSV_FILE_PATH_WITH_DELIMITER_ERR);
        } catch (CricketLeagueAnalyserException e) {
            Assert.assertEquals(CricketLeagueAnalyserException.ExceptionType.SOME_ISSUE_IN_FILE, e.type);
        }
    }

    @Test
    public void givenCricketLeagueRunsCsvFile_SortBySixAndFour_WhenFileIsNotLoaded_ShouldReturnMaxSixthAndFours() {
        try {
            CricketLeagueAnalyser iplAnalyser = new CricketLeagueAnalyser();
            iplAnalyser.getSortData(SortingEnums.StatisticFields.MAX_SIX_AND_FOUR);
        } catch (CricketLeagueAnalyserException e) {
            Assert.assertEquals(CricketLeagueAnalyserException.ExceptionType.DATA_NOT_FOUND, e.type);
        }
    }

    @Test
    public void givenCricketLeagueRunsCsvFile_WhenFileIsCorrect_ShouldReturnBestStrikingRateWithMaxSixthAndFour() {
        try {
            CricketLeagueAnalyser iplAnalyser = new CricketLeagueAnalyser();
            iplAnalyser.loadIPLData(IPLPlayers.BATSMAN, IPL_RUNS_CSV_FILE_PATH);
            String sortedData = iplAnalyser.getSortData(SortingEnums.StatisticFields.BEST_STRIKING_RATE_WITH_SIX_AND_FOUR);
            IPLBatsmanCSV[] censusCSV = new Gson().fromJson(sortedData, IPLBatsmanCSV[].class);
            Assert.assertEquals("Andre Russell", censusCSV[0].player);
        } catch (CricketLeagueAnalyserException e) {
        }
    }

    @Test
    public void givenCricketLeagueRunsCsvFile_SortByBestStrikingRate_WhenFileIsWrong_ShouldThrowingException() {
        try {
            CricketLeagueAnalyser iplAnalyser = new CricketLeagueAnalyser();
            iplAnalyser.loadIPLData(IPLPlayers.BATSMAN, IPL_RUNS_CSV_FILE_PATH_WITH_WRONG_FILE);
        } catch (CricketLeagueAnalyserException e) {
            Assert.assertEquals(CricketLeagueAnalyserException.ExceptionType.NO_SUCH_FILE, e.type);
        }
    }

    @Test
    public void givenCricketLeagueRunsCsvFile_SortByBestStrikingRate__WhenFileIsNotLoaded_ShouldReturnMaxSixthAndFours() {
        try {
            CricketLeagueAnalyser iplAnalyser = new CricketLeagueAnalyser();
            iplAnalyser.getSortData(SortingEnums.StatisticFields.BEST_STRIKING_RATE_WITH_SIX_AND_FOUR);
        } catch (CricketLeagueAnalyserException e) {
            Assert.assertEquals(CricketLeagueAnalyserException.ExceptionType.DATA_NOT_FOUND, e.type);
        }
    }

    @Test
    public void givenCricketLeagueRunsCsvFile_WhenFileIsCorrect_ShouldReturnBestAvgWithBestStrikingRate() {
        try {
            CricketLeagueAnalyser iplAnalyser = new CricketLeagueAnalyser();
            iplAnalyser.loadIPLData(IPLPlayers.BATSMAN, IPL_RUNS_CSV_FILE_PATH);
            String sortedData = iplAnalyser.getSortData(SortingEnums.StatisticFields.BEST_AVG_WITH_BEST_STRIKING);
            IPLBatsmanCSV[] censusCSV = new Gson().fromJson(sortedData, IPLBatsmanCSV[].class);
            Assert.assertEquals("MS Dhoni", censusCSV[0].player);
        } catch (CricketLeagueAnalyserException e) {
        }
    }

    @Test
    public void givenCricketLeagueRunsCsvFile_SortByBestAvgWithStrikingRate__WhenFileIsNotLoaded_ShouldThrowingException() {
        try {
            CricketLeagueAnalyser iplAnalyser = new CricketLeagueAnalyser();
            iplAnalyser.getSortData(SortingEnums.StatisticFields.BEST_AVG_WITH_BEST_STRIKING);
        } catch (CricketLeagueAnalyserException e) {
            Assert.assertEquals(CricketLeagueAnalyserException.ExceptionType.DATA_NOT_FOUND, e.type);
        }
    }

    @Test
    public void givenCricketLeagueRunsCsvFile_WhenFileIsCorrect_ShouldReturnMaxRunsWithBestAvg() {
        try {
            CricketLeagueAnalyser iplAnalyser = new CricketLeagueAnalyser();
            iplAnalyser.loadIPLData(IPLPlayers.BATSMAN, IPL_RUNS_CSV_FILE_PATH);
            String sortedData = iplAnalyser.getSortData(SortingEnums.StatisticFields.MAX_RUN_WITH_BEST_AVG);
            IPLBatsmanCSV[] censusCSV = new Gson().fromJson(sortedData, IPLBatsmanCSV[].class);
            Assert.assertEquals("David Warner ", censusCSV[0].player);
        } catch (CricketLeagueAnalyserException e) {
        }
    }

    @Test
    public void givenCricketLeagueRunsCsvFile_SortByMaxRunWithBestAvg_WhenFileIsNotLoaded_ShouldThrowingException() {
        try {
            CricketLeagueAnalyser iplAnalyser = new CricketLeagueAnalyser();
            iplAnalyser.getSortData(SortingEnums.StatisticFields.MAX_RUN_WITH_BEST_AVG);
        } catch (CricketLeagueAnalyserException e) {
            Assert.assertEquals(CricketLeagueAnalyserException.ExceptionType.DATA_NOT_FOUND, e.type);
        }
    }

    @Test
    public void givenCricketLeagueWiktsCsvFile_WhenFileCorrect_ShouldReturnTopBowlerAvg() {
        try {
            CricketLeagueAnalyser iplAnalyser = new CricketLeagueAnalyser();
            iplAnalyser.loadIPLData(IPLPlayers.BOWLER, IPL_WKTS_CSV_FILE_PATH);
            String sortedData = iplAnalyser.getSortData(SortingEnums.StatisticFields.BOWLING_AVERAGE);
            IPLBowlerCSV[] censusCSV = new Gson().fromJson(sortedData, IPLBowlerCSV[].class);
            Assert.assertEquals("Suresh Raina", censusCSV[0].player);
        } catch (CricketLeagueAnalyserException e) {
        }
    }

    @Test
    public void givenCricketLeagueWiktsCsvFile_WhenFileWrong_ShouldThrowingException() {
        CricketLeagueAnalyser iplAnalyser = new CricketLeagueAnalyser();
        try {
            iplAnalyser.loadIPLData(IPLPlayers.BOWLER, IPL_WKTS_CSV_FILE_PATH);
            iplAnalyser.getSortData(SortingEnums.StatisticFields.BOWLING_AVERAGE);
        } catch (CricketLeagueAnalyserException e) {
            Assert.assertEquals(CricketLeagueAnalyserException.ExceptionType.SOME_ISSUE_IN_FILE, e.type);
        }
    }

    @Test
    public void givenCricketLeagueWiktsCsvFile_WhenFileNotLoaded_ShouldThrowingException() {
        try {
            CricketLeagueAnalyser iplAnalyser = new CricketLeagueAnalyser();
            iplAnalyser.getSortData(SortingEnums.StatisticFields.BOWLING_AVERAGE);
        } catch (CricketLeagueAnalyserException e) {
            Assert.assertEquals(CricketLeagueAnalyserException.ExceptionType.DATA_NOT_FOUND, e.type);
        }
    }

    @Test
    public void givenCricketLeagueWiksCsvFile_WhenFileCorrect_ShouldReturnTopStrikingRateOfBowler() {
        try {
            CricketLeagueAnalyser iplAnalyser = new CricketLeagueAnalyser();
            iplAnalyser.loadIPLData(IPLPlayers.BOWLER, IPL_WKTS_CSV_FILE_PATH);
            String sortedData = iplAnalyser.getSortData(SortingEnums.StatisticFields.BOWLER_STRIKING_RATE);
            IPLBowlerCSV[] censusCSV = new Gson().fromJson(sortedData, IPLBowlerCSV[].class);
            Assert.assertEquals("Suresh Raina", censusCSV[0].player);
        } catch (CricketLeagueAnalyserException e) {
        }
    }

    @Test
    public void givenCricketLeagueWicketsCsvFile_WhenFileCorrect_ShouldReturnBestEconomyRateOfBowler() {
        try {
            CricketLeagueAnalyser iplAnalyser = new CricketLeagueAnalyser();
            iplAnalyser.loadIPLData(IPLPlayers.BOWLER, IPL_WKTS_CSV_FILE_PATH);
            String sortedData = iplAnalyser.getSortData(SortingEnums.StatisticFields.BOWLER_BEST_ECONOMY);
            IPLBowlerCSV[] censusCSV = new Gson().fromJson(sortedData, IPLBowlerCSV[].class);
            Assert.assertEquals("Shivam Dube", censusCSV[0].player);
        } catch (CricketLeagueAnalyserException e) {
        }
    }

    @Test
    public void givenCricketLeagueWicketsCsvFile_WhenFileCorrect_ShouldReturnBestStrikingRateWith5wAnd4w() {
        try {
            CricketLeagueAnalyser iplAnalyser = new CricketLeagueAnalyser();
            iplAnalyser.loadIPLData(IPLPlayers.BOWLER, IPL_WKTS_CSV_FILE_PATH);
            String sortedData = iplAnalyser.getSortData(SortingEnums.StatisticFields.BEST_STRIKING_RATE_WITH_4W_AND_5W);
            IPLBowlerCSV[] censusCSV = new Gson().fromJson(sortedData, IPLBowlerCSV[].class);
            Assert.assertEquals("Kagiso Rabada", censusCSV[0].player);
        } catch (CricketLeagueAnalyserException e) {
        }
    }

    @Test
    public void givenCricketLeagueWicketsCsvFile_WhenFileCorrect_ShouldReturnBestBowlingAverageWithBestStrikingRate() {
        try {
            CricketLeagueAnalyser iplAnalyser = new CricketLeagueAnalyser();
            iplAnalyser.loadIPLData(IPLPlayers.BOWLER, IPL_WKTS_CSV_FILE_PATH);
            String sortedData = iplAnalyser.getSortData(SortingEnums.StatisticFields.BEST_BOWLING_AVERAGE_WITH_STRIKING_RATE);
            IPLBowlerCSV[] censusCSV = new Gson().fromJson(sortedData, IPLBowlerCSV[].class);
            Assert.assertEquals("Suresh Raina", censusCSV[0].player);
        } catch (CricketLeagueAnalyserException e) {
        }
    }

    @Test
    public void givenCricketLeagueWicketsCsvFile_WhenFileCorrect_ShouldReturnBestMaximumWicketsWithBestAvg() {
        try {
            CricketLeagueAnalyser iplAnalyser = new CricketLeagueAnalyser();
            iplAnalyser.loadIPLData(IPLPlayers.BOWLER, IPL_WKTS_CSV_FILE_PATH);
            String sortedData = iplAnalyser.getSortData(SortingEnums.StatisticFields.MAX_WICKETS_With_BEST_AVG);
            IPLBowlerCSV[] censusCSV = new Gson().fromJson(sortedData, IPLBowlerCSV[].class);
            Assert.assertEquals("Suresh Raina", censusCSV[0].player);
        } catch (CricketLeagueAnalyserException e) {
        }
    }
}
