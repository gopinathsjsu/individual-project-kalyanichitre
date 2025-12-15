package com.project.logprocessor;

public class LogParserFactory {

    public static LogParser getParser(String line) {
        if (line.contains("metric=")) return new APMLogParser();
        if (line.contains("level=")) return new ApplicationLogParser();
        if (line.contains("request_method=")) return new RequestLogParser();
        return null; // corrupted or unknown
    }
}
