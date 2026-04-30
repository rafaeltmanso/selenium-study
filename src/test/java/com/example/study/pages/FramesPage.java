package com.example.study.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FramesPage {

    private static final String BASE_URL = "https://theinternet-tau.vercel.app/examples/frames";
    private static final By PAGE_TITLE = By.xpath("//h1[normalize-space()='Frames']");
    private static final By IFRAME = By.cssSelector("main iframe");

    private final WebDriver driver;
    private final WebDriverWait wait;

    public FramesPage(WebDriver driver, WebDriverWait wait) {
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

    public WebElement getIframe() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(IFRAME));
    }

    public void switchToIframe() {
        WebElement iframe = getIframe();
        driver.switchTo().frame(iframe);
    }

    public void switchToDefaultContent() {
        driver.switchTo().defaultContent();
    }

    public String getIframeContentText() {
        switchToIframe();
        WebElement heading = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1")));
        String text = heading.getText().trim();
        switchToDefaultContent();
        return text;
    }
}