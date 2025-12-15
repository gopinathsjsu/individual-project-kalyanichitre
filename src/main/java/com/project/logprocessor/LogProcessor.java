package com.project.logprocessor;

import java.io.*;
import java.util.*;

public class LogProcessor {

    private final String filename;

    private final APMAggregator apmAggregator = new APMAggregator();
    private final ApplicationAggregator appAggregator = new ApplicationAggregator();
    private final RequestAggregator reqAggregator = new RequestAggregator();

    public LogProcessor(String filename) {
        this.filename = filename;
    }

    public void process() {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;

            while ((line = br.readLine()) != null) {
                LogParser parser = LogParserFactory.getParser(line);

                if (parser == null) continue;

                parser.parse(line, apmAggregator, appAggregator, reqAggregator);
            }

            apmAggregator.writeJson("output/apm.json");
            appAggregator.writeJson("output/application.json");
            reqAggregator.writeJson("output/request.json");

            System.out.println("Processing complete.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
