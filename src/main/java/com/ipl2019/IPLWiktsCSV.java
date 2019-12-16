package com.ipl2019;

import com.opencsv.bean.CsvBindByName;

public class IPLWiktsCSV {

    @CsvBindByName(column = "PLAYER")
    public String player;

    @CsvBindByName(column = "Avg")
    public double avg;

    @CsvBindByName(column = "SR")
    public double strikeRate;

    public IPLWiktsCSV() {
    }
}
