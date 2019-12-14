package com.ipl2019;

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
    public void givenCricketLeagueRunsCsvFile_WhenCorrectRecord_ShouldReturnRecordCount() {
        try {
            CricketLeagueAnalyser iplAnalyser = new CricketLeagueAnalyser();
            int numOFRecord = iplAnalyser.loadILPData(IPL_RUNS_CSV_FILE_PATH);
            Assert.assertEquals(101, numOFRecord);
        } catch (CricketLeagueAnalyserException e) {
        }
    }

    @Test
    public void givenCricketLeagueRunsCsvFile_WhenFileIsWrong_ShouldThrowingException() {
        try {
            CricketLeagueAnalyser iplAnalyser = new CricketLeagueAnalyser();
            iplAnalyser.loadILPData(IPL_RUNS_CSV_FILE_PATH_WITH_WRONG_FILE);
        } catch (CricketLeagueAnalyserException e) {
            Assert.assertEquals(CricketLeagueAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
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

}
