package com.ipl2019;

import com.opencsv.bean.CsvBindByName;

public class IPLBatsmanCSV {

    @CsvBindByName(column = "PLAYER", required = true)
    public String player;

    @CsvBindByName(column = "Runs", required = true)
    public int run;

    @CsvBindByName(column = "Avg")
    public double avg;

    @CsvBindByName(column = "6s",required = true)
    public int six;

    @CsvBindByName(column = "4s",required = true)
    public int four;

    @CsvBindByName(column = "SR",required = true)
    public double strikeRate;

    public IPLBatsmanCSV() {
    }
}
