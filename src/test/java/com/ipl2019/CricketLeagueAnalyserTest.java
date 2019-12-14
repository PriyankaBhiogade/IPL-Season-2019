package com.ipl2019;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

public class CricketLeagueAnalyserTest {

    private static final String OLD_IPL_RUNS_CSV_FILE_PATH = "./src/test/resources/IPL2019FactsheetMostRuns.csv";
    private static final String NEW_IPL_RUNS_CSV_FILE_PATH = "./src/test/resources/NewIPL2019FactsheetMostRuns.csv";
    private static final String IPL_RUNS_CSV_FILE_PATH_WITH_WRONG_FILE = "IPL2019FactsheetMostRuns123.csv";
    private static final String IPL_RUNS_CSV_FILE_PATH_WITH_DELIMITER_ERR = "./src/test/resources/IPL2019FactsheetMostRunsWithDelimiterErr.csv";
    private static final String IPL_RUNS_CSV_FILE_PATH_WITH_HEADER_ERR = "./src/test/resources/IPL2019FactsheetMostRunsWithHeaderError.csv";
    private static final String EMPTY_FILE_PATH = "./src/test/resources/EmptyFile.csv";
    private static final String IPL_WKTS_CSV_FILE_PATH = "./src/test/resources/IPL2019FactsheetMostWkts.csv";

    @Test
    public void givenCricketLeagueRunsCsvFile_WhenCorrect_ShouldReturnNewFile() {
        CricketLeagueAnalyser iplAnalyser = new CricketLeagueAnalyser();
        iplAnalyser.prepareFileData(OLD_IPL_RUNS_CSV_FILE_PATH);
    }

    @Test
    public void givenCricketLeagueRunsCsvFile_WhenCorrectRecord_ShouldReturnRecordCount() {
        try {
            CricketLeagueAnalyser iplAnalyser = new CricketLeagueAnalyser();
            Map<String, IPLRunsDAO> numOFRecord = iplAnalyser.loadILPData(NEW_IPL_RUNS_CSV_FILE_PATH);
            Assert.assertEquals(100, numOFRecord.size());
        } catch (CricketLeagueAnalyserException e) {
        }
    }

    @Test
    public void givenCricketLeagueRunsCsvFile_WhenFileIsWrong_ShouldThrowingException() {
        try {
            CricketLeagueAnalyser iplAnalyser = new CricketLeagueAnalyser();
            iplAnalyser.loadILPData(IPL_RUNS_CSV_FILE_PATH_WITH_WRONG_FILE);
        } catch (CricketLeagueAnalyserException e) {
            Assert.assertEquals(CricketLeagueAnalyserException.ExceptionType.NO_SUCH_FILE, e.type);
        }
    }

    @Test
    public void givenCricketLeagueRunsCsvFile_WhenDelimiterIssue_ShouldThrowingException() {
        try {
            CricketLeagueAnalyser iplAnalyser = new CricketLeagueAnalyser();
            iplAnalyser.loadILPData(IPL_RUNS_CSV_FILE_PATH_WITH_DELIMITER_ERR);
        } catch (CricketLeagueAnalyserException e) {
            Assert.assertEquals(CricketLeagueAnalyserException.ExceptionType.SOME_ISSUE_IN_FILE, e.type);
        }
    }

    @Test
    public void givenCricketLeagueRunsCsvFile_WhenHeaderIssue_ShouldThrowingException() {
        try {
            CricketLeagueAnalyser iplAnalyser = new CricketLeagueAnalyser();
            iplAnalyser.loadILPData(IPL_RUNS_CSV_FILE_PATH_WITH_HEADER_ERR);
        } catch (CricketLeagueAnalyserException e) {
            Assert.assertEquals(CricketLeagueAnalyserException.ExceptionType.ERROR_FROM_CSV_BUILDER, e.type);
        }
    }

    @Test
    public void givenCricketLeagueRunsCsvFile_WhenFileIsEmpty_ShouldThrowingException() {
        try {
            CricketLeagueAnalyser iplAnalyser = new CricketLeagueAnalyser();
            iplAnalyser.loadILPData(EMPTY_FILE_PATH);
        } catch (CricketLeagueAnalyserException e) {
            Assert.assertEquals(CricketLeagueAnalyserException.ExceptionType.ERROR_FROM_CSV_BUILDER, e.type);
        }
    }

    @Test
    public void givenCricketLeagueRunsCsvFile_WhenFileTypeIsWrong_ShouldThrowingException() {
        try {
            CricketLeagueAnalyser iplAnalyser = new CricketLeagueAnalyser();
            iplAnalyser.loadILPData(IPL_WKTS_CSV_FILE_PATH);
        } catch (CricketLeagueAnalyserException e) {
            Assert.assertEquals(CricketLeagueAnalyserException.ExceptionType.ERROR_FROM_CSV_BUILDER, e.type);
        }
    }

    @Test
    public void givenCricketLeagueRunsCsvFile_WhenFileCorrect_ShouldReturnAvg() {
        try {
            CricketLeagueAnalyser iplAnalyser = new CricketLeagueAnalyser();
            iplAnalyser.loadILPData(NEW_IPL_RUNS_CSV_FILE_PATH);
            String sortedData = iplAnalyser.getSortData(CricketAnalyser.StatesticFields.AVERAGE);
            IPLRunsCSV[] censusCSV = new Gson().fromJson(sortedData, IPLRunsCSV[].class);
            Assert.assertEquals("MS Dhoni", censusCSV[0].player);
        } catch (CricketLeagueAnalyserException e) {
        }
    }

    @Test
    public void givenCricketLeagueRunsCsvFile_WhenFileCorrect_ShouldReturnTopStrikingRate() {
        try {
            CricketLeagueAnalyser iplAnalyser = new CricketLeagueAnalyser();
            iplAnalyser.loadILPData(NEW_IPL_RUNS_CSV_FILE_PATH);
            String sortedData = iplAnalyser.getSortData(CricketAnalyser.StatesticFields.STRIKING_RATE);
            IPLRunsCSV[] censusCSV = new Gson().fromJson(sortedData, IPLRunsCSV[].class);
            Assert.assertEquals("Ishant Sharma", censusCSV[0].player);
        } catch (CricketLeagueAnalyserException e) {
        }
    }
}
