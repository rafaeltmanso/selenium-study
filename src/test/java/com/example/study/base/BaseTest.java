package com.example.study.base;

import com.example.study.utils.DriverFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public abstract class BaseTest {

    protected WebDriver driver;
    protected WebDriverWait wait;

    @BeforeEach
    public void setUp() {
        driver = DriverFactory.createDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        beforeEach();
    }

    protected void beforeEach() {
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}