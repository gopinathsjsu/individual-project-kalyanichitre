package com.project.logprocessor;

public class APMLogParser implements LogParser {

    @Override
    public void parse(String line,
                      APMAggregator apmAgg,
                      ApplicationAggregator appAgg,
                      RequestAggregator reqAgg) {

        try {
            String metric = extract(line, "metric");
            double value = Double.parseDouble(extract(line, "value"));
            apmAgg.addMetricValue(metric, value);
        } catch (Exception e) {
            // ignore malformed lines
        }
    }

    private String extract(String line, String key) {
        int start = line.indexOf(key + "=") + key.length() + 1;
        int end = line.indexOf(" ", start);
        return end == -1 ? line.substring(start) : line.substring(start, end);
    }
}
