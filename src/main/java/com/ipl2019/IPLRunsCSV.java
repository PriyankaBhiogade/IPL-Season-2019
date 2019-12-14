package com.ipl2019;

import com.opencsv.bean.CsvBindByName;

public class IPLRunsCSV {

    @CsvBindByName(column = "PLAYER", required = true)
    public String player;

    @CsvBindByName(column = "Runs", required = true)
    public int run;

    @CsvBindByName(column = "Avg")
    public double avg;

    public IPLRunsCSV() {
    }
}
