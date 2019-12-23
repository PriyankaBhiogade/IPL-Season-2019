package com.ipl2019;

import java.util.Comparator;

public class ComparingFieldsForBestBattingBowlingAvg implements Comparator<IPLDAO>{

    @Override
    public int compare(IPLDAO iplDAO1, IPLDAO iplDAO2) {
        return (int) (((iplDAO2.battingAvg)-(1d /iplDAO2.bowlingAvg)) - ((iplDAO1.battingAvg) -(1d /iplDAO1.bowlingAvg )) );
    }
}

