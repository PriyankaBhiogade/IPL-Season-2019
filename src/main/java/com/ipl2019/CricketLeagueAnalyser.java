package com.ipl2019;

import com.google.gson.Gson;
import java.util.*;
import static java.util.stream.Collectors.toCollection;

public class CricketLeagueAnalyser {

    Map<SoringEnums.StatisticFields, Comparator<IPLDAO>> sortBy = null;
    Map<String, IPLDAO> iplRunMap = null;
    private IPLPlayer player;

    public CricketLeagueAnalyser() {
        this.sortBy = new HashMap<>();
        this.iplRunMap = new HashMap<>();
        this.sortBy.put(SoringEnums.StatisticFields.AVERAGE, Comparator.comparing(data -> data.battingAvg, Comparator.reverseOrder()));
        this.sortBy.put(SoringEnums.StatisticFields.STRIKING_RATE, Comparator.comparing(data -> data.strikeRate, Comparator.reverseOrder()));
        this.sortBy.put(SoringEnums.StatisticFields.MAX_SIX_AND_FOUR, new ComparingFields().reversed());
        this.sortBy.put(SoringEnums.StatisticFields.BEST_STRIKING_RATE_WITH_SIX_AND_FOUR, new ComparingFields().reversed().thenComparing(data -> data.strikeRate));
        Comparator<IPLDAO> comp = Comparator.comparing(censusDAO -> censusDAO.battingAvg, Comparator.reverseOrder());
        this.sortBy.put(SoringEnums.StatisticFields.BEST_AVG_WITH_BEST_STRIKING, comp.thenComparing(data -> data.strikeRate, Comparator.reverseOrder()));
        Comparator<IPLDAO> comp1 = Comparator.comparing(censusDAO -> censusDAO.run, Comparator.reverseOrder());
        this.sortBy.put(SoringEnums.StatisticFields.MAX_RUN_WITH_BEST_AVG, comp1.thenComparing(data -> data.battingAvg, Comparator.reverseOrder()));
    }

    public int loadIPLData(IPLPlayer player, String csvFilePath) throws CricketLeagueAnalyserException {
        this.player = player;
        IPLLoaderAdapter censusAdapter = CricketLeagueAnalyserFactory.getPlayer(player);
        iplRunMap = censusAdapter.loadData(csvFilePath);
        return iplRunMap.size();
    }

    public String getSortData(SoringEnums.StatisticFields field) throws CricketLeagueAnalyserException {
        if (iplRunMap == null || iplRunMap.size() == 0) {
            throw new CricketLeagueAnalyserException("No Census Data",
                    CricketLeagueAnalyserException.ExceptionType.DATA_NOT_FOUND);
        }
        ArrayList recordList = iplRunMap.values().stream()
                .sorted(sortBy.get(field))
                .collect(toCollection(ArrayList::new));
        String sortedStateCensusJson = new Gson().toJson(recordList);
        return sortedStateCensusJson;
    }
}
