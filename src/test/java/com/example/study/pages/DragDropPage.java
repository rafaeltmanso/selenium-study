package com.example.study.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;

public class DragDropPage {

    private static final String BASE_URL = "https://theinternet-tau.vercel.app/examples/drag-and-drop";
    private static final By PAGE_TITLE = By.xpath("//h1[normalize-space()='Drag and Drop']");
    private static final By COLUMN_A_HEADER = By.cssSelector("main .grid > div:nth-of-type(1) h2");
    private static final By COLUMN_B_HEADER = By.cssSelector("main .grid > div:nth-of-type(2) h2");
    private static final By COLUMN_A_ITEMS = By.cssSelector("main .grid > div:nth-of-type(1) [draggable='true']");
    private static final By COLUMN_B_ITEMS = By.cssSelector("main .grid > div:nth-of-type(2) [draggable='true']");

    private final WebDriver driver;
    private final WebDriverWait wait;

    public DragDropPage(WebDriver driver, WebDriverWait wait) {
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

    public String getColumnAHeaderText() {
        return driver.findElement(COLUMN_A_HEADER).getText().trim();
    }

    public String getColumnBHeaderText() {
        return driver.findElement(COLUMN_B_HEADER).getText().trim();
    }

    public int getColumnAItemCount() {
        return driver.findElements(COLUMN_A_ITEMS).size();
    }

    public int getColumnBItemCount() {
        return driver.findElements(COLUMN_B_ITEMS).size();
    }

    public List<WebElement> getColumnAItems() {
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(COLUMN_A_ITEMS));
    }

    public List<WebElement> getColumnBItems() {
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(COLUMN_B_ITEMS));
    }

    public void dragItemFromAToB(WebElement source) {
        List<WebElement> targetItems = getColumnBItems();
        WebElement target = targetItems.isEmpty() ?driver.findElement(COLUMN_B_ITEMS) : targetItems.get(0);
        
        new Actions(driver)
            .clickAndHold(source)
            .moveToElement(target)
            .release(target)
            .perform();
    }

    public List<String> getColumnATexts() {
        return getColumnAItems().stream()
            .map(WebElement::getText)
            .map(String::trim)
            .toList();
    }

    public List<String> getColumnBTexts() {
        return getColumnBItems().stream()
            .map(WebElement::getText)
            .map(String::trim)
            .toList();
    }
}