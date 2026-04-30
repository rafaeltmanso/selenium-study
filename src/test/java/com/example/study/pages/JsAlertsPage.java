package com.example.study.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class JsAlertsPage {

    private static final String BASE_URL = "https://theinternet-tau.vercel.app/examples/js-alerts";
    private static final By PAGE_TITLE = By.xpath("//h1[normalize-space()='JavaScript Alerts']");
    private static final By ALERT_BUTTONS = By.cssSelector("main button");

    private final WebDriver driver;
    private final WebDriverWait wait;

    public JsAlertsPage(WebDriver driver, WebDriverWait wait) {
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

    public java.util.List<WebElement> getAlertButtons() {
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(ALERT_BUTTONS));
    }

    public int getAlertButtonCount() {
        return getAlertButtons().size();
    }

    public void clickAlertButton(int index) {
        getAlertButtons().get(index).click();
    }

    public org.openqa.selenium.Alert getAlert() {
        return wait.until(ExpectedConditions.alertIsPresent());
    }

    public void acceptAlert() {
        getAlert().accept();
    }

    public void dismissAlert() {
        getAlert().dismiss();
    }

    public void sendTextToAlert(String text) {
        getAlert().sendKeys(text);
    }
}