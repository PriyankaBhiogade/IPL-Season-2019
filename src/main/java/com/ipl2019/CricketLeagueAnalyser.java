package com.ipl2019;

import com.google.gson.Gson;

import java.util.*;

import static java.util.stream.Collectors.toCollection;

public class CricketLeagueAnalyser {

    Map<SortingEnums.StatisticFields, Comparator<IPLDAO>> sortBy = null;
    Map<String, IPLDAO> iplRunMap = null;
    private IPLPlayers player;

    public CricketLeagueAnalyser() {
        this.sortBy = new HashMap<>();
        this.iplRunMap = new HashMap<>();
        this.sortBy.put(SortingEnums.StatisticFields.BATTING_AVERAGE, Comparator.comparing(data -> data.battingAvg, Comparator.reverseOrder()));
        this.sortBy.put(SortingEnums.StatisticFields.BATSMAN_STRIKING_RATE, Comparator.comparing(data -> data.strikeRate, Comparator.reverseOrder()));
        this.sortBy.put(SortingEnums.StatisticFields.MAX_SIX_AND_FOUR, new ComparingFieldsForBatting().reversed());
        this.sortBy.put(SortingEnums.StatisticFields.BEST_STRIKING_RATE_WITH_SIX_AND_FOUR, new ComparingFieldsForBatting().reversed().thenComparing(data -> data.strikeRate));
        Comparator<IPLDAO> comparingBattingAvg = Comparator.comparing(censusDAO -> censusDAO.battingAvg, Comparator.reverseOrder());
        this.sortBy.put(SortingEnums.StatisticFields.BEST_AVG_WITH_BEST_STRIKING, comparingBattingAvg.thenComparing(data -> data.strikeRate, Comparator.reverseOrder()));
        Comparator<IPLDAO> comparingRuns = Comparator.comparing(censusDAO -> censusDAO.run, Comparator.reverseOrder());
        this.sortBy.put(SortingEnums.StatisticFields.MAX_RUN_WITH_BEST_AVG, comparingRuns.thenComparing(data -> data.battingAvg, Comparator.reverseOrder()));
        this.sortBy.put(SortingEnums.StatisticFields.BOWLING_AVERAGE, Comparator.comparing(data -> data.bowlingAvg));
        this.sortBy.put(SortingEnums.StatisticFields.BOWLER_STRIKING_RATE, Comparator.comparing(data -> data.strikeRate));
        this.sortBy.put(SortingEnums.StatisticFields.BOWLER_BEST_ECONOMY, Comparator.comparing(data -> data.economy));
        this.sortBy.put(SortingEnums.StatisticFields.BEST_STRIKING_RATE_WITH_4W_AND_5W, new ComparingFieldsForBowling().reversed().thenComparing(data -> data.strikeRate));
        Comparator<IPLDAO> comparingBowlingAvg = Comparator.comparing(censusDAO -> censusDAO.bowlingAvg);
        this.sortBy.put(SortingEnums.StatisticFields.BEST_BOWLING_AVERAGE_WITH_STRIKING_RATE, comparingBowlingAvg.thenComparing(data -> data.strikeRate));
        Comparator<IPLDAO> comparingWickets = Comparator.comparing(censusDAO -> censusDAO.run, Comparator.reverseOrder());
        this.sortBy.put(SortingEnums.StatisticFields.MAX_WICKETS_With_BEST_AVG, comparingWickets.thenComparing(data -> data.bowlingAvg));


    }

    public int loadIPLData(IPLPlayers player, String csvFilePath) throws CricketLeagueAnalyserException {
        this.player = player;
        IPLLoaderAdapter censusAdapter = GetPlayer.getPlayer(player);
        iplRunMap = censusAdapter.loadData(csvFilePath);
        return iplRunMap.size();
    }

    public String getSortData(SortingEnums.StatisticFields field) throws CricketLeagueAnalyserException {
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
