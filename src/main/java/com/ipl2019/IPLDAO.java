package com.ipl2019;

public class IPLDAO {
    public String player;
    public int run;
    public double battingAvg;
    public double batsmanStrikeRate;
    public double bowlerStrikeRate;
    public int six;
    public int four;
    public double bowlingAvg;
    public double economy;
    public int wickets_4;
    public int wickets_5;
    public int totalWickets;


    public IPLDAO(IPLBatsmanCSV runCSV) {
        this.player = runCSV.player;
        this.run = runCSV.run;
        this.battingAvg = runCSV.avg;
        this.batsmanStrikeRate = runCSV.strikeRate;
        this.six = runCSV.six;
        this.four = runCSV.four;
    }

    public IPLDAO(IPLBowlerCSV wiktsCSV) {
        this.player = wiktsCSV.player;
        this.bowlerStrikeRate = wiktsCSV.strikeRate;
        this.bowlingAvg = wiktsCSV.avg;
        this.economy = wiktsCSV.economy;
        this.wickets_4 = wiktsCSV.wickets_4;
        this.wickets_5 = wiktsCSV.wickets_5;
        this.totalWickets = wiktsCSV.wickets;
    }
}
