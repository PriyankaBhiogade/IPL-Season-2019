package com.ipl2019;

public class CricketLeagueAnalyserException extends Exception {

    ExceptionType type;

    public enum ExceptionType {
        CENSUS_FILE_PROBLEM,ERROR_FROM_CSV_BUILDER
    }

    public CricketLeagueAnalyserException(String message, ExceptionType type) {
        super(message);
        this.type = type;
    }
}
