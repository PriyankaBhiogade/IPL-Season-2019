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

    @CsvBindByName(column = "4w")
    public int wickets_4;

    @CsvBindByName(column = "5w")
    public int wickets_5;

    public IPLBowlerCSV() {
    }
}
