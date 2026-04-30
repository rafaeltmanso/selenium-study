package com.example.study.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;

public class CheckboxesPage {

    private static final String BASE_URL = "https://theinternet-tau.vercel.app/examples/checkboxes";
    private static final By CHECKBOXES = By.cssSelector("input[type='checkbox']");

    private final WebDriver driver;
    private final WebDriverWait wait;

    public CheckboxesPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void open() {
        driver.get(BASE_URL);
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(CHECKBOXES, 1));
    }

    public List<WebElement> getCheckboxes() {
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(CHECKBOXES));
    }

    public boolean isCheckboxChecked(int index) {
        return getCheckboxes().get(index).isSelected();
    }

    public boolean isCheckboxUnchecked(int index) {
        return !isCheckboxChecked(index);
    }

    public void checkCheckbox(int index) {
        getCheckboxes().get(index).click();
    }
}