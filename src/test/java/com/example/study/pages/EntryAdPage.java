package com.example.study.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EntryAdPage {

    private static final String BASE_URL = "https://theinternet-tau.vercel.app/examples/entry-ad";
    private static final By PAGE_TITLE = By.xpath("//h1[normalize-space()='Entry Ad']");
    private static final By MODAL = By.cssSelector(".fixed.inset-0.bg-black\\/50");
    private static final By CLOSE_BUTTON = By.cssSelector(".fixed button");

    private final WebDriver driver;
    private final WebDriverWait wait;

    public EntryAdPage(WebDriver driver, WebDriverWait wait) {
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

    public String getDescription() {
        return driver.findElement(By.cssSelector("main p")).getText();
    }

    public boolean isModalVisible() {
        WebElement modal = wait.until(ExpectedConditions.visibilityOfElementLocated(MODAL));
        return modal.isDisplayed();
    }

    public void clickCloseButton() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(CLOSE_BUTTON));
        button.click();
    }

    public boolean isModalDismissed() {
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(MODAL));
    }
}