package com.ipl2019;

import java.util.Comparator;

public class ComparingFieldsForBatting implements Comparator<IPLDAO> {

    @Override
    public int compare(IPLDAO mostRun1, IPLDAO mostRun2) {
        return ((mostRun1.six * 6) + (mostRun1.four * 4)) - ((mostRun2.six * 6) + (mostRun2.four * 4));
    }
}
