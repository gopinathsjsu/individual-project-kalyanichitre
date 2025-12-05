package com.project.logprocessor;

import org.junit.jupiter.api.Test;
import java.io.File;
import static org.junit.jupiter.api.Assertions.*;

public class EndToEndIntegrationTest {

    @Test
    public void testFullProcessingPipeline() {

        LogProcessor processor = new LogProcessor("input.txt");
        processor.process();

        File apm = new File("output/apm.json");
        File app = new File("output/application.json");
        File req = new File("output/request.json");

        assertTrue(apm.exists(), "APM JSON not created");
        assertTrue(app.exists(), "Application JSON not created");
        assertTrue(req.exists(), "Request JSON not created");
    }
}
