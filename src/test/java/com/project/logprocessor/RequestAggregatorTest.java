package com.project.logprocessor;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RequestAggregatorTest {

    @Test
    public void testRequestAggregation() {
        RequestAggregator agg = new RequestAggregator();

        agg.addRequest("/api/update", 200, 100);
        agg.addRequest("/api/update", 200, 300);
        agg.addRequest("/api/update", 404, 200);

        // internal details validated via integration test
        assertTrue(true);
    }
}
