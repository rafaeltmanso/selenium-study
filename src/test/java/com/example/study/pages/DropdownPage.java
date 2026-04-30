package com.example.study.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;

public class DropdownPage {

    private static final String BASE_URL = "https://theinternet-tau.vercel.app/examples/dropdown";
    private static final By PAGE_TITLE = By.xpath("//h1[normalize-space()='Dropdown List']");
    private static final By DROPDOWN_SELECT = By.cssSelector("main select");

    private final WebDriver driver;
    private final WebDriverWait wait;

    public DropdownPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void open() {
        driver.get(BASE_URL);
        wait.until(ExpectedConditions.visibilityOfElementLocated(PAGE_TITLE));
    }

    public Select getDropdown() {
        WebElement selectElement = wait.until(ExpectedConditions.elementToBeClickable(DROPDOWN_SELECT));
        return new Select(selectElement);
    }

    public String getSelectedOption() {
        return getDropdown().getFirstSelectedOption().getText().trim();
    }

    public List<WebElement> getAllOptions() {
        return getDropdown().getOptions();
    }

    public void selectByVisibleText(String text) {
        getDropdown().selectByVisibleText(text);
    }
}