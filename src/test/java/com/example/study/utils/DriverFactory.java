package com.example.study.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public final class DriverFactory {

    private static final int IMPLICIT_WAIT_SECONDS = 10;
    private static final int PAGE_LOAD_TIMEOUT_SECONDS = 30;

    private DriverFactory() {
    }

    public static WebDriver createDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");

        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(IMPLICIT_WAIT_SECONDS));
        driver.manage().timeouts().pageLoadTimeout(java.time.Duration.ofSeconds(PAGE_LOAD_TIMEOUT_SECONDS));

        return driver;
    }
}