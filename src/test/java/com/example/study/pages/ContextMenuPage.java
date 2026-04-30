package com.example.study.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ContextMenuPage {

    private static final String BASE_URL = "https://theinternet-tau.vercel.app/examples/context-menu";
    private static final By PAGE_TITLE = By.xpath("//h1[normalize-space()='Context Menu']");
    private static final By CONTEXT_MENU_BOX = By.cssSelector("main .cursor-context-menu");
    private static final By STATUS_TEXT = By.xpath("//p[contains(normalize-space(), 'Context menu triggered:')]");

    private final WebDriver driver;
    private final WebDriverWait wait;

    public ContextMenuPage(WebDriver driver, WebDriverWait wait) {
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

    public WebElement getContextMenuBox() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(CONTEXT_MENU_BOX));
    }

    public String getStatusText() {
        return driver.findElement(STATUS_TEXT).getText();
    }

    public void triggerContextMenu() {
        WebElement box = getContextMenuBox();
        new Actions(driver).contextClick(box).perform();
    }

    public boolean isStatusUpdated() {
        wait.until(ExpectedConditions.not(ExpectedConditions.textToBe(STATUS_TEXT, "Context menu triggered: None yet")));
        return getStatusText().contains("Context menu triggered:");
    }
}