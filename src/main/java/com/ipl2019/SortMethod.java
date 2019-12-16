package com.ipl2019;

import java.util.Comparator;

public class SortMethod implements Comparator<IPLRunsDAO> {

    @Override
    public int compare(IPLRunsDAO mostRun1, IPLRunsDAO mostRun2) {
        return ((mostRun1.six * 6) + (mostRun1.four * 4)) - ((mostRun2.six * 6) + (mostRun2.four * 4));
    }
}
