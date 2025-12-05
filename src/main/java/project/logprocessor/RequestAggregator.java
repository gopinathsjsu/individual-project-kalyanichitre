package com.project.logprocessor;

import com.google.gson.Gson;
import java.io.FileWriter;
import java.util.*;

public class RequestAggregator {

    private static class Data {
        List<Integer> times = new ArrayList<>();
        Map<String, Integer> statusCounts = new HashMap<>();
    }

    private final Map<String, Data> routes = new HashMap<>();

    public void addRequest(String route, int status, int time) {
        Data data = routes.computeIfAbsent(route, k -> new Data());
        data.times.add(time);

        String code = status / 100 + "XX";
        data.statusCounts.put(code, data.statusCounts.getOrDefault(code, 0) + 1);
    }

    public void writeJson(String path) {

        Map<String, Object> output = new HashMap<>();

        for (String route : routes.keySet()) {
            Data data = routes.get(route);
            Collections.sort(data.times);

            Map<String, Object> stats = new HashMap<>();
            stats.put("min", data.times.get(0));
            stats.put("max", data.times.get(data.times.size() - 1));

            stats.put("50_percentile", percentile(data.times, 50));
            stats.put("90_percentile", percentile(data.times, 90));
            stats.put("95_percentile", percentile(data.times, 95));
            stats.put("99_percentile", percentile(data.times, 99));

            Map<String, Object> routeOutput = new HashMap<>();
            routeOutput.put("response_times", stats);
            routeOutput.put("status_codes", data.statusCounts);

            output.put(route, routeOutput);
        }

        try (FileWriter writer = new FileWriter(path)) {
            writer.write(new Gson().toJson(output));
        } catch (Exception e) {}
    }

    private int percentile(List<Integer> list, int p) {
        int index = (int)Math.ceil((p / 100.0) * list.size()) - 1;
        return list.get(Math.max(0, index));
    }
}
