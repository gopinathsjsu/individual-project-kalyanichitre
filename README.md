[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/_ffg77_l)

# Log Processing CLI Application

This project is a Java command-line application that reads a mixed log file and separates the log entries into three categories:

- APM Logs (system performance metrics)
- Application Logs (INFO, DEBUG, WARNING, ERROR messages)
- Request Logs (HTTP method, URL, response time, status code)

Each log type is parsed, aggregated, and written into its own JSON file inside the output/ directory.

## Features

- Automatically detects log type using a Factory pattern.
- Parses each log type using Strategy pattern (separate parser classes).
- Aggregates data:
  - APM: min, max, median, average per metric
  - Application: count of logs per severity level
  - Request: min/max response time, percentiles, HTTP status code categories
- Outputs:
  output/apm.json
  output/application.json
  output/request.json

## Project Structure

src/
  main/java/com/project/logprocessor/
    Main.java
    LogProcessor.java
    LogParser.java
    LogParserFactory.java
    APMLogParser.java
    ApplicationLogParser.java
    RequestLogParser.java
    APMAggregator.java
    ApplicationAggregator.java
    RequestAggregator.java

  test/java/com/project/logprocessor/
    LogParserFactoryTest.java
    APMAggregatorTest.java
    ApplicationAggregatorTest.java
    RequestAggregatorTest.java
    EndToEndIntegrationTest.java

design_document.pdf
input.txt
pom.xml
output/
README.md

## How to Run

Compile:
mvn clean package

Run:
java -jar target/logprocessor-1.0-SNAPSHOT.jar --file input.txt

This generates:
output/apm.json
output/application.json
output/request.json

## Run Tests

mvn test

## Design Patterns

Factory Pattern: selects correct parser for each log line.
Strategy Pattern: each log type has its own parser implementation.
Singleton (optional): aggregator manager.

Details and UML diagram are in design_document.pdf.