package org.task;


import io.github.bonigarcia.seljup.SeleniumJupiter;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.RegisterExtension;

import java.io.File;
import java.io.FileReader;
import java.util.Properties;


@ExtendWith(SeleniumJupiter.class)
public class BaseTest {
    protected static Properties properties;
    @RegisterExtension
    static SeleniumJupiter seleniumJupiter = new SeleniumJupiter();

    @SneakyThrows
    @BeforeAll
    static void setUp() {
        File file = new File("src/main/resources/configsQa.properties");
        properties = new Properties();
        properties.load(new FileReader(file));
    }

    @AfterEach
    void tearDown( ) {
// if failed take screenshot and save log to logs folder
    }
}