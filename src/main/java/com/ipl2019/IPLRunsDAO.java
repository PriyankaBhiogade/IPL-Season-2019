package com.ipl2019;

public class IPLRunsDAO {
    public String player;
    public int run;
    public double battingAvg;
    public double strikeRate;
    public int six;
    public int four;

    public IPLRunsDAO(IPLRunsCSV runCSV) {
        this.player = runCSV.player;
        this.run = runCSV.run;
        this.battingAvg = runCSV.avg;
        this.strikeRate = runCSV.strikeRate;
        this.six = runCSV.six;
        this.four = runCSV.four;
    }

    public IPLRunsDAO(IPLWiktsCSV wiktsCSV) {
        this.player = wiktsCSV.player;
        this.battingAvg = wiktsCSV.avg;
        this.strikeRate = wiktsCSV.strikeRate;
    }
}
