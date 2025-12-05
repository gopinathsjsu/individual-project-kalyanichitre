package com.project.logprocessor;

public class ApplicationLogParser implements LogParser {

    @Override
    public void parse(String line,
                      APMAggregator apmAgg,
                      ApplicationAggregator appAgg,
                      RequestAggregator reqAgg) {

        try {
            String level = extract(line, "level");
            appAgg.increment(level);
        } catch (Exception e) {}
    }

    private String extract(String line, String key) {
        int start = line.indexOf(key + "=") + key.length() + 1;
        int end = line.indexOf(" ", start);
        return end == -1 ? line.substring(start) : line.substring(start, end);
    }
}
