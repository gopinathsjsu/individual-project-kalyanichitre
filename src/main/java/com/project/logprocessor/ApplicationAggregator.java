package com.project.logprocessor;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileWriter;
import java.util.*;

public class ApplicationAggregator {

    private static final Gson PRETTY_GSON = new GsonBuilder().setPrettyPrinting().create();

    private final Map<String, Integer> counts = new HashMap<>();

    public void increment(String level) {
        counts.put(level, counts.getOrDefault(level, 0) + 1);
    }

    public void writeJson(String path) {
        try (FileWriter writer = new FileWriter(path)) {
            writer.write(PRETTY_GSON.toJson(counts));
        } catch (Exception e) {}
    }
}
