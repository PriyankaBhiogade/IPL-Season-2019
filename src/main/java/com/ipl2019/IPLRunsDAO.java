package com.ipl2019;

public class IPLRunsDAO {
    public String player;
    public int run;
    public double avg;
    public double strikeRate;

    public IPLRunsDAO(IPLRunsCSV runCSV) {
        this.player = runCSV.player;
        this.run = runCSV.run;
        this.avg = runCSV.avg;
        this.strikeRate = runCSV.strikeRate;
    }
}
