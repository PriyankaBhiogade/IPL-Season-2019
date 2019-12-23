package com.ipl2019;

import java.util.Map;

public class MergeDataAdapter extends IPLLoaderAdapter {

    @Override
    public Map<String, IPLDAO> loadData(String... csvFilePath) throws CricketLeagueAnalyserException {
        Map<String, IPLDAO> battingMap = super.loadData(IPLBatsmanCSV.class, csvFilePath[0]);
        Map<String, IPLDAO> bowlingMap = super.loadData(IPLBowlerCSV.class, csvFilePath[1]);
        bowlingMap.values().stream()
                .filter(iplRanData -> battingMap.get(iplRanData.player) != null)
                .forEach(iplData -> battingMap.get(iplData.player).bowlingAvg = iplData.bowlingAvg);
        return battingMap;
    }
}
