package com.example.study.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DynamicLoadingPage {

    private static final String BASE_URL = "https://theinternet-tau.vercel.app/examples/dynamic-loading";
    private static final By PAGE_TITLE = By.xpath("//h1[normalize-space()='Dynamic Loading']");
    private static final By START_BUTTON = By.cssSelector("button.px-6.py-3.bg-primary");
    private static final By LOADED_CONTENT = By.xpath("//p[contains(text(), 'Hello World')]");

    private final WebDriver driver;
    private final WebDriverWait wait;

    public DynamicLoadingPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void open() {
        driver.get(BASE_URL);
        wait.until(ExpectedConditions.visibilityOfElementLocated(PAGE_TITLE));
    }

    public boolean isPageLoaded() {
        WebElement title = wait.until(ExpectedConditions.visibilityOfElementLocated(PAGE_TITLE));
        return title.isDisplayed();
    }

    public void clickStartButton() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(START_BUTTON));
        button.click();
    }

    public WebElement getLoadedContent() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(LOADED_CONTENT));
    }

    public boolean isContentLoaded() {
        WebElement content = getLoadedContent();
        return content.isDisplayed() && !content.getText().isEmpty();
    }
}