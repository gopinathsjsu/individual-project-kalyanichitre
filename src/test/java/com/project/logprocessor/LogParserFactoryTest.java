package com.project.logprocessor;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LogParserFactoryTest {

    @Test
    public void testAPMParserDetection() {
        String line = "timestamp=2024 metric=cpu_usage_percent value=72";
        assertTrue(LogParserFactory.getParser(line) instanceof APMLogParser);
    }

    @Test
    public void testApplicationParserDetection() {
        String line = "timestamp=2024 level=INFO message=\"Test\"";
        assertTrue(LogParserFactory.getParser(line) instanceof ApplicationLogParser);
    }

    @Test
    public void testRequestParserDetection() {
        String line = "timestamp=2024 request_method=GET request_url=\"/home\" response_status=200 response_time_ms=25";
        assertTrue(LogParserFactory.getParser(line) instanceof RequestLogParser);
    }

    @Test
    public void testUnknownParser() {
        String line = "this is corrupted nonsense line";
        assertNull(LogParserFactory.getParser(line));
    }
}
