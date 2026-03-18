package org.task;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class BaseTestApi extends BaseTest {

    protected static ApiClient apiClient;

    @BeforeAll
    static void setUp() {
        apiClient = new ApiClient(properties.getProperty("apitimeout"));
    }

    @AfterEach
    void tearDown() {

    }
}