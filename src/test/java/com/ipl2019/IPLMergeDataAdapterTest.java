package com.ipl2019;

import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

public class IPLMergeDataAdapterTest {
    private static final String IPL_WKTS_CSV_FILE_PATH = "./src/test/resources/IPL2019FactsheetMostWkts.csv";
    private static final String IPL_WIKTS_CSV_FILE_PATH_WITH_WRONG_FILE = "IPL2019FactsheetMostRuns123.csv";
    private static final String IPL_WIKTS_CSV_FILE_PATH_WITH_DELIMITER_ERR = "./src/test/resources/IPL2019FactsheetMostWktsWithDelimiterErr.csv";
    private static final String IPL_WIKTS_CSV_FILE_PATH_WITH_HEADER_ERR = "./src/test/resources/IPL2019FactsheetMostWktsWithHeaderErr.csv";
    private static final String EMPTY_FILE_PATH = "./src/test/resources/EmptyFile.csv";
    private static final String IPL_RUNS_CSV_FILE_PATH = "./src/test/resources/IPL2019FactsheetMostRuns.csv";


    @Test
    public void givenCricketLeagueRunsCsvFileAndCricketLeagueWiktCsvFile_WhenCorrectRecord_ShouldReturnRecordCount() {
        try {
            MergeDataAdapter iplAnalyser = new MergeDataAdapter();
            Map<String, IPLDAO> numOFRecord = iplAnalyser.loadData(IPL_RUNS_CSV_FILE_PATH, IPL_WKTS_CSV_FILE_PATH);
            Assert.assertEquals(100, numOFRecord.size());
        } catch (CricketLeagueAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenCricketLeagueRunsCsvFileAndCricketLeagueWiktCsvFile_WhenFileIsWrong_ShouldThrowingException() {
        try {
            MergeDataAdapter iplAnalyser = new MergeDataAdapter();
            iplAnalyser.loadData(IPL_WIKTS_CSV_FILE_PATH_WITH_WRONG_FILE,IPL_WKTS_CSV_FILE_PATH);
        } catch (CricketLeagueAnalyserException e) {
            Assert.assertEquals(CricketLeagueAnalyserException.ExceptionType.NO_SUCH_FILE, e.type);
        }
    }

    @Test
    public void givenCricketLeagueRunsCsvFileAndCricketLeagueWiktCsvFile_WhenDelimiterIssue_ShouldThrowingException() {
        try {
            MergeDataAdapter iplAnalyser = new MergeDataAdapter();
            iplAnalyser.loadData(IPL_WIKTS_CSV_FILE_PATH_WITH_DELIMITER_ERR,IPL_WKTS_CSV_FILE_PATH);
        } catch (CricketLeagueAnalyserException e) {
            Assert.assertEquals(CricketLeagueAnalyserException.ExceptionType.ERROR_FROM_CSV_BUILDER, e.type);
        }
    }

    @Test
    public void givenCricketLeagueRunsCsvFileAndCricketLeagueWiktCsvFile_WhenHeaderIssue_ShouldThrowingException() {
        try {
            MergeDataAdapter iplAnalyser = new MergeDataAdapter();
            iplAnalyser.loadData(IPL_WIKTS_CSV_FILE_PATH_WITH_HEADER_ERR,IPL_WKTS_CSV_FILE_PATH);
        } catch (CricketLeagueAnalyserException e) {
            Assert.assertEquals(CricketLeagueAnalyserException.ExceptionType.ERROR_FROM_CSV_BUILDER, e.type);
        }
    }

    @Test
    public void givenCricketLeagueRunsCsvFileAndCricketLeagueWiktCsvFile_WhenFileIsEmpty_ShouldThrowingException() {
        try {
            MergeDataAdapter iplAnalyser = new MergeDataAdapter();
            iplAnalyser.loadData(EMPTY_FILE_PATH,IPL_WKTS_CSV_FILE_PATH);
        } catch (CricketLeagueAnalyserException e) {
            Assert.assertEquals(CricketLeagueAnalyserException.ExceptionType.ERROR_FROM_CSV_BUILDER, e.type);
        }
    }

    @Test
    public void givenCricketLeagueRunsCsvFileAndCricketLeagueWiktCsvFile_WhenFileTypeIsWrong_ShouldThrowingException() {
        try {
            MergeDataAdapter iplAnalyser = new MergeDataAdapter();
            iplAnalyser.loadData(IPL_RUNS_CSV_FILE_PATH,IPL_WKTS_CSV_FILE_PATH);
        } catch (CricketLeagueAnalyserException e) {
            Assert.assertEquals(CricketLeagueAnalyserException.ExceptionType.ERROR_FROM_CSV_BUILDER, e.type);
        }
    }
}
