package com.ipl2019;

import com.opencsv.bean.CsvBindByName;

public class IPLRunsCSV {

    @CsvBindByName(column = "PLAYER", required = true)
    public String player;

    @CsvBindByName(column = "Runs", required = true)
    public int run;

    @CsvBindByName(column = "Avg")
    public double avg;

    @CsvBindByName(column = "6s",required = true)
    public int six;

    public IPLRunsCSV() {
    }
}
