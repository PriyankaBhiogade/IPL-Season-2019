package com.ipl2019;

import java.util.Map;

public class WiktsAdapter extends IPLLoaderAdapter {

    @Override
    public Map<String, IPLDAO> loadData(String csvFilePath) throws CricketLeagueAnalyserException {
        Map<String, IPLDAO> iplMap = super.loadData(IPLWiktsCSV.class, csvFilePath);
        return iplMap;
    }
}
