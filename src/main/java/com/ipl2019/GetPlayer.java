package com.ipl2019;

public class GetPlayer {

    public static IPLLoaderAdapter getPlayer(IPLPlayers player) throws CricketLeagueAnalyserException {
        if (player.equals(IPLPlayers.BATSMAN))
            return new IPLBatsmanAdapter();
        else if (player.equals(IPLPlayers.BOWLER))
            return new IPLBowlerAdapter();
        else
            throw new CricketLeagueAnalyserException("Incorrect Field", CricketLeagueAnalyserException.ExceptionType.INVALID_TYPE);
    }
}
