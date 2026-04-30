package com.example.study.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FileUploadPage {

    private static final String BASE_URL = "https://theinternet-tau.vercel.app/examples/file-upload";
    private static final By PAGE_TITLE = By.xpath("//h1[normalize-space()='File Uploader']");
    private static final By FILE_INPUT = By.cssSelector("input[type='file']");

    private final WebDriver driver;
    private final WebDriverWait wait;

    public FileUploadPage(WebDriver driver, WebDriverWait wait) {
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

    public WebElement getChooseFileButton() {
        return driver.findElement(By.cssSelector("main button"));
    }

    public String getChooseFileButtonText() {
        return getChooseFileButton().getText().trim();
    }

    public void uploadFile(String filePath) {
        WebElement input = driver.findElement(FILE_INPUT);
        input.sendKeys(filePath);
    }
}