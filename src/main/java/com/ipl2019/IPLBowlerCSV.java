package com.ipl2019;

import com.opencsv.bean.CsvBindByName;

public class IPLBowlerCSV {

    @CsvBindByName(column = "PLAYER")
    public String player;

    @CsvBindByName(column = "Avg")
    public double avg;

    @CsvBindByName(column = "SR")
    public double strikeRate;

    @CsvBindByName(column = "Econ")
    public double economy;

    public IPLBowlerCSV() {
    }
}
