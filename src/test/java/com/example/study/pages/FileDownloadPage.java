package com.example.study.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;

public class FileDownloadPage {

    private static final String BASE_URL = "https://theinternet-tau.vercel.app/examples/file-download";
    private static final By PAGE_TITLE = By.xpath("//h1[normalize-space()='File Download']");
    private static final By DOWNLOAD_BUTTONS = By.cssSelector("main button");

    private final WebDriver driver;
    private final WebDriverWait wait;

    public FileDownloadPage(WebDriver driver, WebDriverWait wait) {
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

    public String getDescription() {
        return driver.findElement(By.cssSelector("main p")).getText();
    }

    public List<WebElement> getDownloadButtons() {
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(DOWNLOAD_BUTTONS));
    }

    public int getDownloadButtonCount() {
        return getDownloadButtons().size();
    }
}