package com.example.study.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddRemovePage {

    private static final String BASE_URL = "https://theinternet-tau.vercel.app/examples/add-remove-elements";
    private static final By ADD_BUTTON = By.xpath("//button[text()='Add Element']");
    private static final By DELETE_BUTTON = By.xpath("//button[text()='Delete']");

    private final WebDriver driver;
    private final WebDriverWait wait;

    public AddRemovePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void open() {
        driver.get(BASE_URL);
    }

    public void clickAddElement() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(ADD_BUTTON));
        button.click();
    }

    public boolean isDeleteButtonVisible() {
        WebElement button = wait.until(ExpectedConditions.visibilityOfElementLocated(DELETE_BUTTON));
        return button.isDisplayed();
    }

    public void clickDelete() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(DELETE_BUTTON));
        button.click();
    }

    public boolean isDeleteButtonGone() {
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(DELETE_BUTTON));
    }
}