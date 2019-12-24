package com.ipl2019;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class IplAnalyserMockitoTest {

    private static final String IPL_RUNS_CSV_FILE_PATH = "./src/test/resources/IPL2019FactsheetMostRuns.csv";
    private static final String IPL_WKTS_CSV_FILE_PATH = "./src/test/resources/IPL2019FactsheetMostWkts.csv";

    @Mock
    IPLLoaderAdapter iplLoaderAdapter;
    CricketLeagueAnalyser cricketLeagueAnalyser;
    Map<String, IPLDAO> iplRanMap;
    Map<String, IPLDAO> iplWiktMap;

    @Before
    public void getSetup() {
        cricketLeagueAnalyser = new CricketLeagueAnalyser();
        iplLoaderAdapter = mock(IPLLoaderAdapter.class);
        cricketLeagueAnalyser.setIPLAdapter(iplLoaderAdapter);
        iplRanMap = new HashMap<>();
        iplRanMap.put("11", new IPLDAO("MS Dhoni", 416, 50.2, 23.0));
        iplRanMap.put("12", new IPLDAO("KL Rahul", 529, 53.9, 20.3));
        iplRanMap.put("13", new IPLDAO("Hardik Pandya", 402, 44.66, 10.0));
        iplWiktMap = new HashMap<>();
        iplWiktMap.put("10", new IPLDAO("Ishant Sharma", 416, 83.2, 23.0));
        iplWiktMap.put("20", new IPLDAO("Rahul Chahar", 400, 45.9, 45.3));
        iplWiktMap.put("30", new IPLDAO("Yusuf Pathan", 300, 30.66, 70.2));
        iplRanMap.put("40", new IPLDAO("Hardik Pandya", 390, 27.85, 5.2));
    }

    @Test
    public void givenBowlerSampleData_WhenProper_ShouldReturnCount() {
        try {
            when(iplLoaderAdapter.loadData(IPL_WKTS_CSV_FILE_PATH)).thenReturn(iplWiktMap);
            int iplActualData = cricketLeagueAnalyser.loadIPLData(IPLPlayers.BOWLER, IPL_WKTS_CSV_FILE_PATH);
            Assert.assertEquals(3, iplActualData);
        } catch (CricketLeagueAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenBatsmanSampleData_WhenProper_ShouldReturnCount() {
        try {
            when(iplLoaderAdapter.loadData(IPL_RUNS_CSV_FILE_PATH)).thenReturn(iplRanMap);
            int iplActualData = cricketLeagueAnalyser.loadIPLData(IPLPlayers.BATSMAN, IPL_RUNS_CSV_FILE_PATH);
            Assert.assertEquals(4, iplActualData);
        } catch (CricketLeagueAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenBattingSampleData_WhenFileCorrect_ShouldReturnTopBatsmanAvg() {
        try {
            when(iplLoaderAdapter.loadData(IPL_RUNS_CSV_FILE_PATH)).thenReturn(iplRanMap);
            cricketLeagueAnalyser.loadIPLData(IPLPlayers.BATSMAN, IPL_RUNS_CSV_FILE_PATH);
            String sortedData = cricketLeagueAnalyser.getSortData(SortingEnums.StatisticFields.BATTING_AVERAGE);
            IPLBatsmanCSV[] censusCSV = new Gson().fromJson(sortedData, IPLBatsmanCSV[].class);
            Assert.assertEquals("KL Rahul", censusCSV[0].player);
        } catch (CricketLeagueAnalyserException e) {
        }
    }

    @Test
    public void givenBowlingSampleData_WhenFileCorrect_ShouldReturnTopBowlerAvg() {
        try {
            when(iplLoaderAdapter.loadData(IPL_WKTS_CSV_FILE_PATH)).thenReturn(iplWiktMap);
            cricketLeagueAnalyser.loadIPLData(IPLPlayers.BOWLER, IPL_WKTS_CSV_FILE_PATH);
            String sortedData = cricketLeagueAnalyser.getSortData(SortingEnums.StatisticFields.BOWLING_AVERAGE);
            IPLBowlerCSV[] censusCSV = new Gson().fromJson(sortedData, IPLBowlerCSV[].class);
            Assert.assertEquals("Yusuf Pathan", censusCSV[0].player);
        } catch (CricketLeagueAnalyserException e) {
        }
    }
}