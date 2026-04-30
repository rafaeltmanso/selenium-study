package com.example.study.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DynamicControlsPage {

    private static final String BASE_URL = "https://theinternet-tau.vercel.app/examples/dynamic-controls";
    private static final By PAGE_TITLE = By.xpath("//h1[normalize-space()='Dynamic Controls']");
    private static final By TOGGLE_BUTTON = By.cssSelector("button.px-6.py-3.bg-primary");
    private static final By TEXT_INPUT = By.id("text-input");
    private static final By CHECKBOX = By.id("checkbox");
    private static final By STATUS_TEXT = By.cssSelector("div.p-4.rounded-lg.bg-muted\\/50 p");

    private final WebDriver driver;
    private final WebDriverWait wait;

    public DynamicControlsPage(WebDriver driver, WebDriverWait wait) {
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

    public String getToggleButtonText() {
        return driver.findElement(TOGGLE_BUTTON).getText().trim();
    }

    public boolean isTextInputEnabled() {
        return driver.findElement(TEXT_INPUT).isEnabled();
    }

    public boolean isCheckboxEnabled() {
        return driver.findElement(CHECKBOX).isEnabled();
    }

    public String getStatusText() {
        return driver.findElement(STATUS_TEXT).getText();
    }

    public void clickToggleButton() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(TOGGLE_BUTTON));
        button.click();
    }

    public void waitForToggleButtonText(String expectedText) {
        wait.until(ExpectedConditions.textToBePresentInElementLocated(TOGGLE_BUTTON, expectedText));
    }

    public boolean isControlsEnabled() {
        return isTextInputEnabled() && isCheckboxEnabled();
    }

    public boolean isControlsDisabled() {
        return !isTextInputEnabled() && !isCheckboxEnabled();
    }

    public void typeInTextInput(String text) {
        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(TEXT_INPUT));
        input.clear();
        input.sendKeys(text);
    }

    public String getTextInputValue() {
        return driver.findElement(TEXT_INPUT).getAttribute("value");
    }

    public void clickCheckbox() {
        driver.findElement(CHECKBOX).click();
    }

    public boolean isCheckboxSelected() {
        return driver.findElement(CHECKBOX).isSelected();
    }

    public boolean isStatusShowingEnabled() {
        return getStatusText().contains("Controls Enabled");
    }

    public boolean isStatusShowingDisabled() {
        return getStatusText().contains("Controls Disabled");
    }
}