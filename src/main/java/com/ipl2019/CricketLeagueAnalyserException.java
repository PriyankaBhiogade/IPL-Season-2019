package com.ipl2019;

public class CricketLeagueAnalyserException extends Exception {

    ExceptionType type;

    public enum ExceptionType {
        NO_SUCH_FILE, SOME_ISSUE_IN_FILE, DATA_NOT_FOUND, ERROR_FROM_CSV_BUILDER
    }

    public CricketLeagueAnalyserException(String message, ExceptionType type) {
        super(message);
        this.type = type;
    }
}
