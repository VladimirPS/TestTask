package org.task;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

@ExtendWith(SeleniumJupiter.class)
public class BaseTestUI extends BaseTestApi {
    protected WebDriver driver;

    @SneakyThrows
    @BeforeEach
    void setUpChrome() {
        ChromeOptions options = new ChromeOptions();
        if (Boolean.getBoolean(System.getProperty("headless"))) {
            options.setHeadless(true);
        }
        seleniumJupiter.getConfig().enableScreenshotWhenFailure();
        this.driver = new ChromeDriver(options);
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }
}