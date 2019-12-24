package com.ipl2019;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CricketLeagueAnalyserTest {

    private static final String IPL_RUNS_CSV_FILE_PATH = "./src/test/resources/IPL2019FactsheetMostRuns.csv";
    private static final String IPL_RUNS_CSV_FILE_PATH_WITH_WRONG_FILE = "IPL2019FactsheetMostRuns123.csv";
    private static final String IPL_RUNS_CSV_FILE_PATH_WITH_DELIMITER_ERR = "./src/test/resources/IPL2019FactsheetMostRunsWithDelimiterErr.csv";
    private static final String EMPTY_FILE_PATH = "./src/test/resources/EmptyFile.csv";
    private static final String IPL_WKTS_CSV_FILE_PATH = "./src/test/resources/IPL2019FactsheetMostWkts.csv";
    private static final String sampleRun = "./src/test/resources/SampleRuns.csv";
    private static final String samplewkts = "./src/test/resources/SampleWkts.csv";

    CricketLeagueAnalyser iplAnalyser;

    @Before
    public void setUp() {
        iplAnalyser = new CricketLeagueAnalyser();
    }

    @Test
    public void givenCricketLeagueRunsCsvFile_WhenFileCorrect_ShouldReturnTopBatsmanAvg() {
        try {
            iplAnalyser = new CricketLeagueAnalyser();
            iplAnalyser.setIPLAdapter(new IPLBatsmanAdapter());
            iplAnalyser.loadIPLData(IPLPlayers.BATSMAN, IPL_RUNS_CSV_FILE_PATH);
            String sortedData = iplAnalyser.getSortData(SortingEnums.StatisticFields.BATTING_AVERAGE);
            IPLBatsmanCSV[] censusCSV = new Gson().fromJson(sortedData, IPLBatsmanCSV[].class);
            Assert.assertEquals("MS Dhoni", censusCSV[0].player);
        } catch (CricketLeagueAnalyserException e) {
        }
    }

    @Test
    public void givenCricketLeagueRunsCsvFile_WhenFileWrong_ShouldThrowingException() {
        try {
            iplAnalyser.setIPLAdapter(new IPLBatsmanAdapter());
            iplAnalyser.loadIPLData(IPLPlayers.BATSMAN, IPL_RUNS_CSV_FILE_PATH);
            iplAnalyser.getSortData(SortingEnums.StatisticFields.BATTING_AVERAGE);
        } catch (CricketLeagueAnalyserException e) {
            Assert.assertEquals(CricketLeagueAnalyserException.ExceptionType.SOME_ISSUE_IN_FILE, e.type);
        }
    }

    @Test
    public void givenCricketLeagueRunsCsvFile_WhenFileNotLoaded_ShouldThrowingException() {
        try {
            iplAnalyser.getSortData(SortingEnums.StatisticFields.BATTING_AVERAGE);
        } catch (CricketLeagueAnalyserException e) {
            Assert.assertEquals(CricketLeagueAnalyserException.ExceptionType.DATA_NOT_FOUND, e.type);
        }
    }

    @Test
    public void givenCricketLeagueRunsCsvFile_WhenFileCorrect_ShouldReturnTopStrikingRate() {
        try {
            iplAnalyser.setIPLAdapter(new IPLBatsmanAdapter());
            iplAnalyser.loadIPLData(IPLPlayers.BATSMAN, IPL_RUNS_CSV_FILE_PATH);
            String sortedData = iplAnalyser.getSortData(SortingEnums.StatisticFields.BATSMAN_STRIKING_RATE);
            IPLBatsmanCSV[] censusCSV = new Gson().fromJson(sortedData, IPLBatsmanCSV[].class);
            Assert.assertEquals("Ishant Sharma", censusCSV[0].player);
        } catch (CricketLeagueAnalyserException e) {
        }
    }

    @Test
    public void givenCricketLeagueRunsCsvFile_WhenFileIsWrong_ShouldThrowException() {
        try {
            iplAnalyser.setIPLAdapter(new IPLBatsmanAdapter());
            iplAnalyser.loadIPLData(IPLPlayers.BATSMAN, EMPTY_FILE_PATH);
            iplAnalyser.getSortData(SortingEnums.StatisticFields.BATSMAN_STRIKING_RATE);
        } catch (CricketLeagueAnalyserException e) {
            Assert.assertEquals(CricketLeagueAnalyserException.ExceptionType.ERROR_FROM_CSV_BUILDER, e.type);
        }
    }

    @Test
    public void givenCricketLeagueRunsCsvFile_WhenFileNotLoaded_ShouldThrowException() {
        try {
            iplAnalyser.getSortData(SortingEnums.StatisticFields.BATSMAN_STRIKING_RATE);
        } catch (CricketLeagueAnalyserException e) {
            Assert.assertEquals(CricketLeagueAnalyserException.ExceptionType.DATA_NOT_FOUND, e.type);
        }
    }

    @Test
    public void givenCricketLeagueRunsCsvFile_SortBySixAndFour_WhenFileCorrect_ShouldReturnMaxSixthAndFours() {
        try {
            iplAnalyser.setIPLAdapter(new IPLBatsmanAdapter());
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
            iplAnalyser.setIPLAdapter(new IPLBatsmanAdapter());
            iplAnalyser.loadIPLData(IPLPlayers.BATSMAN, IPL_RUNS_CSV_FILE_PATH_WITH_DELIMITER_ERR);
        } catch (CricketLeagueAnalyserException e) {
            Assert.assertEquals(CricketLeagueAnalyserException.ExceptionType.SOME_ISSUE_IN_FILE, e.type);
        }
    }

    @Test
    public void givenCricketLeagueRunsCsvFile_SortBySixAndFour_WhenFileIsNotLoaded_ShouldReturnMaxSixthAndFours() {
        try {
            iplAnalyser.setIPLAdapter(new IPLBatsmanAdapter());
            iplAnalyser.getSortData(SortingEnums.StatisticFields.MAX_SIX_AND_FOUR);
        } catch (CricketLeagueAnalyserException e) {
            Assert.assertEquals(CricketLeagueAnalyserException.ExceptionType.DATA_NOT_FOUND, e.type);
        }
    }

    @Test
    public void givenCricketLeagueRunsCsvFile_WhenFileIsCorrect_ShouldReturnBestStrikingRateWithMaxSixthAndFour() {
        try {
            iplAnalyser.setIPLAdapter(new IPLBatsmanAdapter());
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
            iplAnalyser.setIPLAdapter(new IPLBatsmanAdapter());
            iplAnalyser.loadIPLData(IPLPlayers.BATSMAN, IPL_RUNS_CSV_FILE_PATH_WITH_WRONG_FILE);
        } catch (CricketLeagueAnalyserException e) {
            Assert.assertEquals(CricketLeagueAnalyserException.ExceptionType.NO_SUCH_FILE, e.type);
        }
    }

    @Test
    public void givenCricketLeagueRunsCsvFile_SortByBestStrikingRate__WhenFileIsNotLoaded_ShouldReturnMaxSixthAndFours() {
        try {
            iplAnalyser.setIPLAdapter(new IPLBatsmanAdapter());
            iplAnalyser.getSortData(SortingEnums.StatisticFields.BEST_STRIKING_RATE_WITH_SIX_AND_FOUR);
        } catch (CricketLeagueAnalyserException e) {
            Assert.assertEquals(CricketLeagueAnalyserException.ExceptionType.DATA_NOT_FOUND, e.type);
        }
    }

    @Test
    public void givenCricketLeagueRunsCsvFile_WhenFileIsCorrect_ShouldReturnBestAvgWithBestStrikingRate() {
        try {
            iplAnalyser.setIPLAdapter(new IPLBatsmanAdapter());
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
            iplAnalyser.setIPLAdapter(new IPLBatsmanAdapter());
            iplAnalyser.getSortData(SortingEnums.StatisticFields.BEST_AVG_WITH_BEST_STRIKING);
        } catch (CricketLeagueAnalyserException e) {
            Assert.assertEquals(CricketLeagueAnalyserException.ExceptionType.DATA_NOT_FOUND, e.type);
        }
    }

    @Test
    public void givenCricketLeagueRunsCsvFile_WhenFileIsCorrect_ShouldReturnMaxRunsWithBestAvg() {
        try {
            iplAnalyser.setIPLAdapter(new IPLBatsmanAdapter());
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
            iplAnalyser.setIPLAdapter(new IPLBatsmanAdapter());
            iplAnalyser.getSortData(SortingEnums.StatisticFields.MAX_RUN_WITH_BEST_AVG);
        } catch (CricketLeagueAnalyserException e) {
            Assert.assertEquals(CricketLeagueAnalyserException.ExceptionType.DATA_NOT_FOUND, e.type);
        }
    }

    @Test
    public void givenCricketLeagueWiktsCsvFile_WhenFileCorrect_ShouldReturnTopBowlerAvg() {
        try {
            iplAnalyser.setIPLAdapter(new IPLBowlerAdapter());
            iplAnalyser.loadIPLData(IPLPlayers.BOWLER, IPL_WKTS_CSV_FILE_PATH);
            String sortedData = iplAnalyser.getSortData(SortingEnums.StatisticFields.BOWLING_AVERAGE);
            IPLBowlerCSV[] censusCSV = new Gson().fromJson(sortedData, IPLBowlerCSV[].class);
            Assert.assertEquals("Suresh Raina", censusCSV[0].player);
        } catch (CricketLeagueAnalyserException e) {
        }
    }

    @Test
    public void givenCricketLeagueWiktsCsvFile_WhenFileWrong_ShouldThrowingException() {
        try {
            iplAnalyser.setIPLAdapter(new IPLBowlerAdapter());
            iplAnalyser.loadIPLData(IPLPlayers.BOWLER, IPL_WKTS_CSV_FILE_PATH);
            iplAnalyser.getSortData(SortingEnums.StatisticFields.BOWLING_AVERAGE);
        } catch (CricketLeagueAnalyserException e) {
            Assert.assertEquals(CricketLeagueAnalyserException.ExceptionType.SOME_ISSUE_IN_FILE, e.type);
        }
    }

    @Test
    public void givenCricketLeagueWiktsCsvFile_WhenFileNotLoaded_ShouldThrowingException() {
        try {
            iplAnalyser.setIPLAdapter(new IPLBowlerAdapter());
            iplAnalyser.getSortData(SortingEnums.StatisticFields.BOWLING_AVERAGE);
        } catch (CricketLeagueAnalyserException e) {
            Assert.assertEquals(CricketLeagueAnalyserException.ExceptionType.DATA_NOT_FOUND, e.type);
        }
    }

    @Test
    public void givenCricketLeagueWiksCsvFile_WhenFileCorrect_ShouldReturnTopStrikingRateOfBowler() {
        try {
            iplAnalyser.setIPLAdapter(new IPLBowlerAdapter());
            iplAnalyser.loadIPLData(IPLPlayers.BOWLER, IPL_WKTS_CSV_FILE_PATH);
            String sortedData = iplAnalyser.getSortData(SortingEnums.StatisticFields.BOWLER_STRIKING_RATE);
            IPLBowlerCSV[] censusCSV = new Gson().fromJson(sortedData, IPLBowlerCSV[].class);
            Assert.assertEquals("Umesh Yadav", censusCSV[0].player);
        } catch (CricketLeagueAnalyserException e) {
        }
    }

    @Test
    public void givenCricketLeagueWicketsCsvFile_WhenFileCorrect_ShouldReturnBestEconomyRateOfBowler() {
        try {
            iplAnalyser.setIPLAdapter(new IPLBowlerAdapter());
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
            iplAnalyser.setIPLAdapter(new IPLBowlerAdapter());
            iplAnalyser.loadIPLData(IPLPlayers.BOWLER, IPL_WKTS_CSV_FILE_PATH);
            String sortedData = iplAnalyser.getSortData(SortingEnums.StatisticFields.BEST_STRIKING_RATE_WITH_4W_AND_5W);
            IPLBowlerCSV[] censusCSV = new Gson().fromJson(sortedData, IPLBowlerCSV[].class);
            Assert.assertEquals("Lasith Malinga", censusCSV[0].player);
        } catch (CricketLeagueAnalyserException e) {
        }
    }

    @Test
    public void givenCricketLeagueWicketsCsvFile_WhenFileCorrect_ShouldReturnBestBowlingAverageWithBestStrikingRate() {
        try {
            iplAnalyser.setIPLAdapter(new IPLBowlerAdapter());
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
            iplAnalyser.setIPLAdapter(new IPLBowlerAdapter());
            iplAnalyser.loadIPLData(IPLPlayers.BOWLER, IPL_WKTS_CSV_FILE_PATH);
            String sortedData = iplAnalyser.getSortData(SortingEnums.StatisticFields.MAX_WICKETS_With_BEST_AVG);
            IPLBowlerCSV[] censusCSV = new Gson().fromJson(sortedData, IPLBowlerCSV[].class);
            Assert.assertEquals("Suresh Raina", censusCSV[0].player);
        } catch (CricketLeagueAnalyserException e) {
        }
    }

    @Test
    public void givenCricketLeagueWicketsCsvFileAndCricketLeagueRunsCsvFile_WhenFileCorrect_ShouldReturnBestMaximumWicketsWithBestAvg() {
        try {
            iplAnalyser.setIPLAdapter(new MergeDataAdapter());
            iplAnalyser.loadIPLData(IPLPlayers.MERGE_BOTH, IPL_RUNS_CSV_FILE_PATH, IPL_WKTS_CSV_FILE_PATH);
            String sortedData = iplAnalyser.getSortData(SortingEnums.StatisticFields.BEST_BATTING_AVG_AND_BOWLING_AVG);
            IPLBowlerCSV[] censusCSV = new Gson().fromJson(sortedData, IPLBowlerCSV[].class);
            Assert.assertEquals("Andre Russell", censusCSV[0].player);
        } catch (CricketLeagueAnalyserException e) {
        }
    }

    @Test
    public void givenCricketLeagueWicketsCsvFileAndCricketLeagueRunsCsvFile_WhenFileCorrect_ShouldReturnBestCricketerName() {
        try {
            iplAnalyser.setIPLAdapter(new MergeDataAdapter());
            iplAnalyser.loadIPLData(IPLPlayers.MERGE_BOTH, IPL_RUNS_CSV_FILE_PATH, IPL_WKTS_CSV_FILE_PATH);
            String sortedData = iplAnalyser.getSortData(SortingEnums.StatisticFields.BEST_CRICKETER);
            IPLBowlerCSV[] censusCSV = new Gson().fromJson(sortedData, IPLBowlerCSV[].class);
            Assert.assertEquals("Hardik Pandya", censusCSV[0].player);
        } catch (CricketLeagueAnalyserException e) {
            e.printStackTrace();
        }
    }
}
