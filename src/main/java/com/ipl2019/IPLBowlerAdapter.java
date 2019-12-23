package com.ipl2019;

import java.util.Map;

public class IPLBowlerAdapter extends IPLLoaderAdapter {

    @Override
    public Map<String, IPLDAO> loadData(String... csvFilePath) throws CricketLeagueAnalyserException {
        Map<String, IPLDAO> iplMap = super.loadData(IPLBowlerCSV.class, csvFilePath[0]);
        return iplMap;
    }
}
