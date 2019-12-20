package com.ipl2019;

public class CricketLeagueAnalyserFactory {

    public static IPLLoaderAdapter getPlayer(IPLPlayer player) throws CricketLeagueAnalyserException {
        if (player.equals(IPLPlayer.BATSMAN))
            return new RunAdapter();
        else if (player.equals(IPLPlayer.BOWLER))
            return new WiktsAdapter();
        else
            throw new CricketLeagueAnalyserException("Incorrect Field", CricketLeagueAnalyserException.ExceptionType.INVALID_TYPE);
    }
}
