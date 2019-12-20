package com.ipl2019;

import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

public class IPLBowlerAdapterTest {

    private static final String IPL_WKTS_CSV_FILE_PATH = "./src/test/resources/IPL2019FactsheetMostWkts.csv";
    private static final String IPL_WIKTS_CSV_FILE_PATH_WITH_WRONG_FILE = "IPL2019FactsheetMostRuns123.csv";
    private static final String IPL_WIKTS_CSV_FILE_PATH_WITH_DELIMITER_ERR = "./src/test/resources/IPL2019FactsheetMostWktsWithDelimiterErr.csv";
    private static final String IPL_WIKTS_CSV_FILE_PATH_WITH_HEADER_ERR = "./src/test/resources/IPL2019FactsheetMostWktsWithHeaderErr.csv";
    private static final String EMPTY_FILE_PATH = "./src/test/resources/EmptyFile.csv";
    private static final String IPL_RUNS_CSV_FILE_PATH = "./src/test/resources/IPL2019FactsheetMostRuns.csv";

    @Test
    public void givenCricketLeagueWiktsCsvFile_WhenCorrectRecord_ShouldReturnRecordCount() {
        try {
            IPLBowlerAdapter bowlerAdapter = new IPLBowlerAdapter();
            Map<String, IPLDAO>  numOFRecord = bowlerAdapter.loadData(IPL_WKTS_CSV_FILE_PATH);
            Assert.assertEquals(99, numOFRecord.size());
        } catch (CricketLeagueAnalyserException e) {
        }
    }

    @Test
    public void givenCricketLeagueWiktsCsvFile_WhenFileIsWrong_ShouldThrowingException() {
        try {
            IPLBowlerAdapter bowlerAdapter = new IPLBowlerAdapter();
            bowlerAdapter.loadData(IPL_WIKTS_CSV_FILE_PATH_WITH_WRONG_FILE);
        } catch (CricketLeagueAnalyserException e) {
            Assert.assertEquals(CricketLeagueAnalyserException.ExceptionType.NO_SUCH_FILE, e.type);
        }
    }

    @Test
    public void givenCricketLeagueWiktsCsvFile_WhenDelimiterIssue_ShouldThrowingException() {
        try {
            IPLBowlerAdapter bowlerAdapter = new IPLBowlerAdapter();
            bowlerAdapter.loadData(IPL_WIKTS_CSV_FILE_PATH_WITH_DELIMITER_ERR);
        } catch (CricketLeagueAnalyserException e) {
            Assert.assertEquals(CricketLeagueAnalyserException.ExceptionType.SOME_ISSUE_IN_FILE, e.type);
        }
    }

    @Test
    public void givenCricketLeagueWktsCsvFile_WhenHeaderIssue_ShouldThrowingException() {
        try {
            IPLBowlerAdapter bowlerAdapter = new IPLBowlerAdapter();
            bowlerAdapter.loadData(IPL_WIKTS_CSV_FILE_PATH_WITH_HEADER_ERR);
        } catch (CricketLeagueAnalyserException e) {
            Assert.assertEquals(CricketLeagueAnalyserException.ExceptionType.ERROR_FROM_CSV_BUILDER, e.type);
        }
    }

    @Test
    public void givenCricketLeagueWktsCsvFile_WhenFileIsEmpty_ShouldThrowingException() {
        try {
            IPLBowlerAdapter bowlerAdapter = new IPLBowlerAdapter();
            bowlerAdapter.loadData(EMPTY_FILE_PATH);
        } catch (CricketLeagueAnalyserException e) {
            Assert.assertEquals(CricketLeagueAnalyserException.ExceptionType.ERROR_FROM_CSV_BUILDER, e.type);
        }
    }

    @Test
    public void givenCricketLeagueWktsCsvFile_WhenFileTypeIsWrong_ShouldThrowingException() {
        try {
            IPLBowlerAdapter bowlerAdapter = new IPLBowlerAdapter();
            bowlerAdapter.loadData(IPL_RUNS_CSV_FILE_PATH);
        } catch (CricketLeagueAnalyserException e) {
            Assert.assertEquals(CricketLeagueAnalyserException.ExceptionType.ERROR_FROM_CSV_BUILDER, e.type);
        }
    }
}
