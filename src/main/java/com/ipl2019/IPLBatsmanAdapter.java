package com.ipl2019;

import java.util.Map;

public class IPLBatsmanAdapter extends IPLLoaderAdapter {

    @Override
    public Map<String, IPLDAO> loadData(String... csvFilePath) throws CricketLeagueAnalyserException {
        Map<String, IPLDAO> iplMap = super.loadData(IPLBatsmanCSV.class, csvFilePath[0]);
        return iplMap;
    }
}
