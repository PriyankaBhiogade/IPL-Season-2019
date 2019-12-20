package com.ipl2019;

public class IPLDAO {
    public String player;
    public int run;
    public double battingAvg;
    public double strikeRate;
    public int six;
    public int four;
    public double bowlingAvg;
    public double economy;

    public IPLDAO(IPLBatsmanCSV runCSV) {
        this.player = runCSV.player;
        this.run = runCSV.run;
        this.battingAvg = runCSV.avg;
        this.strikeRate = runCSV.strikeRate;
        this.six = runCSV.six;
        this.four = runCSV.four;
    }

    public IPLDAO(IPLBowlerCSV wiktsCSV) {
        this.player = wiktsCSV.player;
        this.battingAvg = wiktsCSV.avg;
        this.strikeRate = wiktsCSV.strikeRate;
        this.bowlingAvg = wiktsCSV.avg;
        this.economy = wiktsCSV.economy;
    }
}
