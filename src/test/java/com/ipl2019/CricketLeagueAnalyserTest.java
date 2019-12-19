package com.ipl2019;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

public class CricketLeagueAnalyserTest {

    private static final String OLD_IPL_RUNS_CSV_FILE_PATH = "./src/test/resources/IPL2019FactsheetMostRuns.csv";
    private static final String IPL_RUNS_CSV_FILE_PATH_WITH_WRONG_FILE = "IPL2019FactsheetMostRuns123.csv";
    private static final String IPL_RUNS_CSV_FILE_PATH_WITH_DELIMITER_ERR = "./src/test/resources/IPL2019FactsheetMostRunsWithDelimiterErr.csv";
    private static final String IPL_RUNS_CSV_FILE_PATH_WITH_HEADER_ERR = "./src/test/resources/IPL2019FactsheetMostRunsWithHeaderError.csv";
    private static final String EMPTY_FILE_PATH = "./src/test/resources/EmptyFile.csv";
    private static final String IPL_WKTS_CSV_FILE_PATH = "./src/test/resources/IPL2019FactsheetMostWkts.csv";

    @Test
    public void givenCricketLeagueRunsCsvFile_WhenCorrect_ShouldReturnNewFile() {
        CricketLeagueAnalyser iplAnalyser = new CricketLeagueAnalyser();
        try {
            iplAnalyser.prepareFileData(OLD_IPL_RUNS_CSV_FILE_PATH);
        } catch (CricketLeagueAnalyserException e) {
        }
    }

    @Test
    public void givenCricketLeagueRunsCsvFile_WhenFileIsWrong_ShouldReturnNewFile() {
        CricketLeagueAnalyser iplAnalyser = new CricketLeagueAnalyser();
        try {
            iplAnalyser.prepareFileData(IPL_RUNS_CSV_FILE_PATH_WITH_WRONG_FILE);
        } catch (CricketLeagueAnalyserException e) {
            Assert.assertEquals(CricketLeagueAnalyserException.ExceptionType.NO_SUCH_FILE, e.type);
        }
    }

    @Test
    public void givenCricketLeagueRunsCsvFile_WhenCorrectRecord_ShouldReturnRecordCount() {
        try {
            CricketLeagueAnalyser iplAnalyser = new CricketLeagueAnalyser();
            int numOFRecord = iplAnalyser.loadIPLRunsData(OLD_IPL_RUNS_CSV_FILE_PATH);
            Assert.assertEquals(100, numOFRecord);
        } catch (CricketLeagueAnalyserException e) {
        }
    }

    @Test
    public void givenCricketLeagueRunsCsvFile_WhenFileIsWrong_ShouldThrowingException() {
        try {
            CricketLeagueAnalyser iplAnalyser = new CricketLeagueAnalyser();
            iplAnalyser.loadIPLRunsData(IPL_RUNS_CSV_FILE_PATH_WITH_WRONG_FILE);
        } catch (CricketLeagueAnalyserException e) {
            Assert.assertEquals(CricketLeagueAnalyserException.ExceptionType.NO_SUCH_FILE, e.type);
        }
    }

    @Test
    public void givenCricketLeagueRunsCsvFile_WhenDelimiterIssue_ShouldThrowingException() {
        try {
            CricketLeagueAnalyser iplAnalyser = new CricketLeagueAnalyser();
            iplAnalyser.loadIPLRunsData(IPL_RUNS_CSV_FILE_PATH_WITH_DELIMITER_ERR);
        } catch (CricketLeagueAnalyserException e) {
            Assert.assertEquals(CricketLeagueAnalyserException.ExceptionType.SOME_ISSUE_IN_FILE, e.type);
        }
    }

    @Test
    public void givenCricketLeagueRunsCsvFile_WhenHeaderIssue_ShouldThrowingException() {
        try {
            CricketLeagueAnalyser iplAnalyser = new CricketLeagueAnalyser();
            iplAnalyser.loadIPLRunsData(IPL_RUNS_CSV_FILE_PATH_WITH_HEADER_ERR);
        } catch (CricketLeagueAnalyserException e) {
            Assert.assertEquals(CricketLeagueAnalyserException.ExceptionType.ERROR_FROM_CSV_BUILDER, e.type);
        }
    }

    @Test
    public void givenCricketLeagueRunsCsvFile_WhenFileIsEmpty_ShouldThrowingException() {
        try {
            CricketLeagueAnalyser iplAnalyser = new CricketLeagueAnalyser();
            iplAnalyser.loadIPLRunsData(EMPTY_FILE_PATH);
        } catch (CricketLeagueAnalyserException e) {
            Assert.assertEquals(CricketLeagueAnalyserException.ExceptionType.ERROR_FROM_CSV_BUILDER, e.type);
        }
    }

    @Test
    public void givenCricketLeagueRunsCsvFile_WhenFileTypeIsWrong_ShouldThrowingException() {
        try {
            CricketLeagueAnalyser iplAnalyser = new CricketLeagueAnalyser();
            iplAnalyser.loadIPLRunsData(IPL_WKTS_CSV_FILE_PATH);
        } catch (CricketLeagueAnalyserException e) {
            Assert.assertEquals(CricketLeagueAnalyserException.ExceptionType.ERROR_FROM_CSV_BUILDER, e.type);
        }
    }

    @Test
    public void givenCricketLeagueRunsCsvFile_WhenFileCorrect_ShouldReturnAvg() {
        try {
            CricketLeagueAnalyser iplAnalyser = new CricketLeagueAnalyser();
            iplAnalyser.loadIPLRunsData(OLD_IPL_RUNS_CSV_FILE_PATH);
            String sortedData = iplAnalyser.getSortData(CricketAnalyserENUM.StatisticFields.AVERAGE);
            IPLRunsCSV[] censusCSV = new Gson().fromJson(sortedData, IPLRunsCSV[].class);
            Assert.assertEquals("MS Dhoni", censusCSV[0].player);
        } catch (CricketLeagueAnalyserException e) {
        }
    }

    @Test
    public void givenCricketLeagueRunsCsvFile_WhenFileWrong_ShouldThrowingException() {
        CricketLeagueAnalyser iplAnalyser = new CricketLeagueAnalyser();
        try {
            iplAnalyser.loadIPLRunsData(OLD_IPL_RUNS_CSV_FILE_PATH);
            iplAnalyser.getSortData(CricketAnalyserENUM.StatisticFields.AVERAGE);
        } catch (CricketLeagueAnalyserException e) {
            Assert.assertEquals(CricketLeagueAnalyserException.ExceptionType.SOME_ISSUE_IN_FILE, e.type);
        }
    }

    @Test
    public void givenCricketLeagueRunsCsvFile_WhenFileNotLoaded_ShouldThrowingException() {
        try {
            CricketLeagueAnalyser iplAnalyser = new CricketLeagueAnalyser();
            iplAnalyser.getSortData(CricketAnalyserENUM.StatisticFields.AVERAGE);
        } catch (CricketLeagueAnalyserException e) {
            Assert.assertEquals(CricketLeagueAnalyserException.ExceptionType.DATA_NOT_FOUND, e.type);
        }
    }

    @Test
    public void givenCricketLeagueRunsCsvFile_WhenFileCorrect_ShouldReturnTopStrikingRate() {
        try {
            CricketLeagueAnalyser iplAnalyser = new CricketLeagueAnalyser();
            iplAnalyser.loadIPLRunsData(OLD_IPL_RUNS_CSV_FILE_PATH);
            String sortedData = iplAnalyser.getSortData(CricketAnalyserENUM.StatisticFields.STRIKING_RATE);
            IPLRunsCSV[] censusCSV = new Gson().fromJson(sortedData, IPLRunsCSV[].class);
            Assert.assertEquals("Ishant Sharma", censusCSV[0].player);
        } catch (CricketLeagueAnalyserException e) {
        }
    }

    @Test
    public void givenCricketLeagueRunsCsvFile_WhenFileIsWrong_ShouldThrowException() {
        CricketLeagueAnalyser iplAnalyser = new CricketLeagueAnalyser();
        try {
            iplAnalyser.loadIPLRunsData(EMPTY_FILE_PATH);
            iplAnalyser.getSortData(CricketAnalyserENUM.StatisticFields.STRIKING_RATE);
        } catch (CricketLeagueAnalyserException e) {
            Assert.assertEquals(CricketLeagueAnalyserException.ExceptionType.ERROR_FROM_CSV_BUILDER, e.type);
        }
    }

    @Test
    public void givenCricketLeagueRunsCsvFile_WhenFileNotLoaded_ShouldThrowException() {
        try {
            CricketLeagueAnalyser iplAnalyser = new CricketLeagueAnalyser();
            iplAnalyser.getSortData(CricketAnalyserENUM.StatisticFields.STRIKING_RATE);
        } catch (CricketLeagueAnalyserException e) {
            Assert.assertEquals(CricketLeagueAnalyserException.ExceptionType.DATA_NOT_FOUND, e.type);
        }
    }

    @Test
    public void givenCricketLeagueRunsCsvFile_SortBySixAndFour_WhenFileCorrect_ShouldReturnMaxSixthAndFours() {
        try {
            CricketLeagueAnalyser iplAnalyser = new CricketLeagueAnalyser();
            iplAnalyser.loadIPLRunsData(OLD_IPL_RUNS_CSV_FILE_PATH);
            String sortedData = iplAnalyser.getSortData(CricketAnalyserENUM.StatisticFields.MAX_SIX_AND_FOUR);
            IPLRunsCSV[] censusCSV = new Gson().fromJson(sortedData, IPLRunsCSV[].class);
            Assert.assertEquals("Andre Russell", censusCSV[0].player);
        } catch (CricketLeagueAnalyserException e) {
        }
    }

    @Test
    public void givenCricketLeagueRunsCsvFile_SortBySixAndFour_WhenFileIsWrong_ShouldThrowingException() {
        try {
            CricketLeagueAnalyser iplAnalyser = new CricketLeagueAnalyser();
            iplAnalyser.loadIPLRunsData(IPL_RUNS_CSV_FILE_PATH_WITH_DELIMITER_ERR);
        } catch (CricketLeagueAnalyserException e) {
            Assert.assertEquals(CricketLeagueAnalyserException.ExceptionType.SOME_ISSUE_IN_FILE, e.type);
        }
    }

    @Test
    public void givenCricketLeagueRunsCsvFile_SortBySixAndFour_WhenFileIsNotLoaded_ShouldReturnMaxSixthAndFours() {
        try {
            CricketLeagueAnalyser iplAnalyser = new CricketLeagueAnalyser();
            iplAnalyser.getSortData(CricketAnalyserENUM.StatisticFields.MAX_SIX_AND_FOUR);
        } catch (CricketLeagueAnalyserException e) {
            Assert.assertEquals(CricketLeagueAnalyserException.ExceptionType.DATA_NOT_FOUND, e.type);
        }
    }

    @Test
    public void givenCricketLeagueRunsCsvFile_WhenFileIsCorrect_ShouldReturnBestStrikingRateWithMaxSixthAndFour() {
        try {
            CricketLeagueAnalyser iplAnalyser = new CricketLeagueAnalyser();
            iplAnalyser.loadIPLRunsData(OLD_IPL_RUNS_CSV_FILE_PATH);
            String sortedData = iplAnalyser.getSortData(CricketAnalyserENUM.StatisticFields.BEST_STRIKING_RATE_WITH_SIX_AND_FOUR);
            IPLRunsCSV[] censusCSV = new Gson().fromJson(sortedData, IPLRunsCSV[].class);
            Assert.assertEquals("Andre Russell", censusCSV[0].player);
        } catch (CricketLeagueAnalyserException e) {
        }
    }

    @Test
    public void givenCricketLeagueRunsCsvFile_SortByBestStrikingRate_WhenFileIsWrong_ShouldThrowingException() {
        try {
            CricketLeagueAnalyser iplAnalyser = new CricketLeagueAnalyser();
            iplAnalyser.loadIPLRunsData(IPL_RUNS_CSV_FILE_PATH_WITH_WRONG_FILE);
        } catch (CricketLeagueAnalyserException e) {
            Assert.assertEquals(CricketLeagueAnalyserException.ExceptionType.NO_SUCH_FILE, e.type);
        }
    }

    @Test
    public void givenCricketLeagueRunsCsvFile_SortByBestStrikingRate__WhenFileIsNotLoaded_ShouldReturnMaxSixthAndFours() {
        try {
            CricketLeagueAnalyser iplAnalyser = new CricketLeagueAnalyser();
            iplAnalyser.getSortData(CricketAnalyserENUM.StatisticFields.BEST_STRIKING_RATE_WITH_SIX_AND_FOUR);
        } catch (CricketLeagueAnalyserException e) {
            Assert.assertEquals(CricketLeagueAnalyserException.ExceptionType.DATA_NOT_FOUND, e.type);
        }
    }

    @Test
    public void givenCricketLeagueRunsCsvFile_WhenFileIsCorrect_ShouldReturnBestAvgWithBestStrikingRate() {
        try {
            CricketLeagueAnalyser iplAnalyser = new CricketLeagueAnalyser();
            iplAnalyser.loadIPLRunsData(OLD_IPL_RUNS_CSV_FILE_PATH);
            String sortedData = iplAnalyser.getSortData(CricketAnalyserENUM.StatisticFields.BEST_AVG_WITH_BEST_STRIKING);
            IPLRunsCSV[] censusCSV = new Gson().fromJson(sortedData, IPLRunsCSV[].class);
            Assert.assertEquals("MS Dhoni", censusCSV[0].player);
        } catch (CricketLeagueAnalyserException e) {
        }
    }

    @Test
    public void givenCricketLeagueRunsCsvFile_SortByBestAvgWithStrikingRate__WhenFileIsNotLoaded_ShouldThrowingException() {
        try {
            CricketLeagueAnalyser iplAnalyser = new CricketLeagueAnalyser();
            iplAnalyser.getSortData(CricketAnalyserENUM.StatisticFields.BEST_AVG_WITH_BEST_STRIKING);
        } catch (CricketLeagueAnalyserException e) {
            Assert.assertEquals(CricketLeagueAnalyserException.ExceptionType.DATA_NOT_FOUND, e.type);
        }
    }

    @Test
    public void givenCricketLeagueRunsCsvFile_WhenFileIsCorrect_ShouldReturnMaxRunsWithBestAvg() {
        try {
            CricketLeagueAnalyser iplAnalyser = new CricketLeagueAnalyser();
            iplAnalyser.loadIPLRunsData(OLD_IPL_RUNS_CSV_FILE_PATH);
            String sortedData = iplAnalyser.getSortData(CricketAnalyserENUM.StatisticFields.MAX_RUN_WITH_BEST_AVG);
            IPLRunsCSV[] censusCSV = new Gson().fromJson(sortedData, IPLRunsCSV[].class);
            Assert.assertEquals("David Warner ", censusCSV[0].player);
        } catch (CricketLeagueAnalyserException e) {
        }
    }

    @Test
    public void givenCricketLeagueRunsCsvFile_SortByMaxRunWithBestAvg_WhenFileIsNotLoaded_ShouldThrowingException() {
        try {
            CricketLeagueAnalyser iplAnalyser = new CricketLeagueAnalyser();
            iplAnalyser.getSortData(CricketAnalyserENUM.StatisticFields.MAX_RUN_WITH_BEST_AVG);
        } catch (CricketLeagueAnalyserException e) {
            Assert.assertEquals(CricketLeagueAnalyserException.ExceptionType.DATA_NOT_FOUND, e.type);
        }
    }

    @Test
    public void givenCricketLeagueWiktsCsvFile_WhenCorrectRecord_ShouldReturnRecordCount() {
        try {
            CricketLeagueAnalyser iplAnalyser = new CricketLeagueAnalyser();
            int numOFRecord = iplAnalyser.loadILPWiktsData(IPL_WKTS_CSV_FILE_PATH);
            Assert.assertEquals(100, numOFRecord);
        } catch (CricketLeagueAnalyserException e) {
            e.printStackTrace();
        }
    }
}
