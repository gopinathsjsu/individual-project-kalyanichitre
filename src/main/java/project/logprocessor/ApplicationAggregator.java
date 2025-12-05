package com.project.logprocessor;

import com.google.gson.Gson;
import java.io.FileWriter;
import java.util.*;

public class ApplicationAggregator {

    private final Map<String, Integer> counts = new HashMap<>();

    public void increment(String level) {
        counts.put(level, counts.getOrDefault(level, 0) + 1);
    }

    public void writeJson(String path) {
        try (FileWriter writer = new FileWriter(path)) {
            writer.write(new Gson().toJson(counts));
        } catch (Exception e) {}
    }
}
