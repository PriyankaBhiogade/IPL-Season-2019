package com.ipl2019;

import java.util.Comparator;

public class ComparingFieldsForBestCricketer implements Comparator<IPLDAO> {

    @Override
    public int compare(IPLDAO iplDAO1, IPLDAO iplDAO2) {
        return ( (iplDAO2.run * iplDAO2.totalWickets) - (iplDAO1.run * iplDAO1.totalWickets));
    }
}
