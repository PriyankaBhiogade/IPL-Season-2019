package com.ipl2019;

import java.util.Comparator;

public class ComparingFields implements Comparator<IPLDAO>{

    @Override
    public int compare(IPLDAO iplDAO1, IPLDAO iplDAO2) {
//        return (int) (Math.max((battingAvg.battingAvg),(1/battingAvg.battingAvg )) - Math.max((bowlingAvg.bowlingAvg) ,(1/bowlingAvg.bowlingAvg)));
        return (int) (  ((iplDAO2.bowlingAvg)-(1d /iplDAO2.battingAvg))-((iplDAO1.battingAvg) -(1d /iplDAO1.bowlingAvg )));
    }
}

