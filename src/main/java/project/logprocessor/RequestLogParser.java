package com.project.logprocessor;

public class RequestLogParser implements LogParser {

    @Override
    public void parse(String line,
                      APMAggregator apmAgg,
                      ApplicationAggregator appAgg,
                      RequestAggregator reqAgg) {

        try {
            String route = extract(line, "request_url").replace("\"", "");
            int status = Integer.parseInt(extract(line, "response_status"));
            int time = Integer.parseInt(extract(line, "response_time_ms"));

            reqAgg.addRequest(route, status, time);

        } catch (Exception e) {}
    }

    private String extract(String line, String key) {
        int start = line.indexOf(key + "=") + key.length() + 1;

        int end = line.indexOf(" ", start);
        return end == -1 ? line.substring(start) : line.substring(start, end);
    }
}
