package com.project.logprocessor;

public interface LogParser {
    void parse(String line, 
               APMAggregator apmAgg, 
               ApplicationAggregator appAgg, 
               RequestAggregator reqAgg);
}
