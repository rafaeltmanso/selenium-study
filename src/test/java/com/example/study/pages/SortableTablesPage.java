package com.example.study.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;

public class SortableTablesPage {

    private static final String BASE_URL = "https://theinternet-tau.vercel.app/examples/sortable-tables";
    private static final By PAGE_TITLE = By.xpath("//h1[normalize-space()='Data Tables']");
    private static final By TABLE = By.cssSelector("table");
    private static final By TABLE_HEADERS = By.cssSelector("table th");
    private static final By TABLE_ROWS = By.cssSelector("table tbody tr");

    private final WebDriver driver;
    private final WebDriverWait wait;

    public SortableTablesPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void open() {
        driver.get(BASE_URL);
        wait.until(ExpectedConditions.visibilityOfElementLocated(PAGE_TITLE));
    }

    public boolean isPageLoaded() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(PAGE_TITLE));
        return driver.findElement(TABLE).isDisplayed();
    }

    public List<WebElement> getTableHeaders() {
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(TABLE_HEADERS));
    }

    public List<WebElement> getTableRows() {
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(TABLE_ROWS));
    }

    public int getHeaderCount() {
        return getTableHeaders().size();
    }

    public int getRowCount() {
        return getTableRows().size();
    }

    public List<String> getHeaderTexts() {
        return getTableHeaders().stream()
                .map(WebElement::getText)
                .map(String::trim)
                .toList();
    }

    public void clickHeader(int index) {
        getTableHeaders().get(index).click();
    }

    public String getFirstRowFirstCellText() {
        return getTableRows().get(0).findElement(By.cssSelector("td")).getText();
    }
}