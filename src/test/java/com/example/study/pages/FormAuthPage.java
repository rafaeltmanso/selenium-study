package com.example.study.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FormAuthPage {

    private static final String BASE_URL = "https://theinternet-tau.vercel.app/examples/form-auth";
    private static final By USERNAME_INPUT = By.id("username");
    private static final By PASSWORD_INPUT = By.id("password");
    private static final By LOGIN_BUTTON = By.cssSelector("button[type='submit']");
    private static final By SECURE_AREA_TITLE = By.xpath("//h1[normalize-space()='Secure Area']");
    private static final By LOGOUT_BUTTON = By.xpath("//button[normalize-space()='Logout']");

    private final WebDriver driver;
    private final WebDriverWait wait;

    public FormAuthPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void open() {
        driver.get(BASE_URL);
    }

    public void login(String username, String password) {
        WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(USERNAME_INPUT));
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(PASSWORD_INPUT));
        WebElement loginBtn = wait.until(ExpectedConditions.elementToBeClickable(LOGIN_BUTTON));

        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        loginBtn.click();
    }

    public boolean isSecureAreaLoaded() {
        WebElement title = wait.until(ExpectedConditions.visibilityOfElementLocated(SECURE_AREA_TITLE));
        return title.isDisplayed();
    }

    public boolean isLogoutButtonVisible() {
        WebElement button = wait.until(ExpectedConditions.visibilityOfElementLocated(LOGOUT_BUTTON));
        return button.isDisplayed();
    }

    public boolean containsSuccessMessage() {
        return driver.getPageSource().contains("You logged into a secure area!");
    }
}