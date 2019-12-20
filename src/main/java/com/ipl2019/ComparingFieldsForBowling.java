package com.ipl2019;

import java.util.Comparator;

public class ComparingFieldsForBowling implements Comparator<IPLDAO> {

    @Override
    public int compare(IPLDAO w1, IPLDAO w2) {
        return ((w1.wickets_4 * 4) + (w1.wickets_5 * 5)) - ((w2.wickets_4 * 4) + (w2.wickets_5 * 5));
    }
}
