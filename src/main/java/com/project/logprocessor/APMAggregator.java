package com.project.logprocessor;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileWriter;
import java.util.*;

public class APMAggregator {

    private static final Gson PRETTY_GSON = new GsonBuilder().setPrettyPrinting().create();

    private final Map<String, List<Double>> metrics = new HashMap<>();

    public void addMetricValue(String metric, double value) {
        metrics.computeIfAbsent(metric, k -> new ArrayList<>()).add(value);
    }

    public void writeJson(String path) {
        Map<String, Object> output = new HashMap<>();

        for (String metric : metrics.keySet()) {
            List<Double> values = metrics.get(metric);
            Collections.sort(values);

            Map<String, Double> stats = new HashMap<>();
            stats.put("minimum", values.get(0));
            stats.put("median", values.get(values.size() / 2));
            stats.put("average", values.stream().mapToDouble(a -> a).average().orElse(0));
            stats.put("max", values.get(values.size() - 1));

            output.put(metric, stats);
        }

        try (FileWriter writer = new FileWriter(path)) {
            writer.write(PRETTY_GSON.toJson(output));
        } catch (Exception e) {}
    }
}
