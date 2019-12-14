package com.ipl2019;

import org.junit.Assert;
import org.junit.Test;

public class IPLAnalyserTest {

    private static final String IPL_MOST_RUNS_CSV_FILE_PATH = "./src/test/resources/IPL2019FactsheetMostRuns.csv";

    @Test
    public void givenCricketLeagueRunsCsvFile_WhenCorrectRecord_ShouldReturnRecordCount () {
        IPLAnalyser iplAnalyser = new IPLAnalyser();
        int numOFRecord = iplAnalyser.loadILPData(IPL_MOST_RUNS_CSV_FILE_PATH);
        Assert.assertEquals(102,numOFRecord);
    }
}
