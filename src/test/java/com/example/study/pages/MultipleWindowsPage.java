package com.example.study.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MultipleWindowsPage {

    private static final String BASE_URL = "https://theinternet-tau.vercel.app/examples/multiple-windows";
    private static final By PAGE_TITLE = By.xpath("//h1[normalize-space()='Multiple Windows']");
    private static final By OPEN_BUTTON = By.cssSelector("main button");

    private final WebDriver driver;
    private final WebDriverWait wait;

    public MultipleWindowsPage(WebDriver driver, WebDriverWait wait) {
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

    public WebElement getOpenButton() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(OPEN_BUTTON));
    }

    public void clickOpenButton() {
        getOpenButton().click();
    }

    public void switchToNewWindow() {
        String mainWindow = driver.getWindowHandle();
        wait.until(d -> driver.getWindowHandles().size() > 1);
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(mainWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
    }

    public void closeAndSwitchBack() {
        String currentWindow = driver.getWindowHandle();
        driver.close();
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(currentWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
    }
}