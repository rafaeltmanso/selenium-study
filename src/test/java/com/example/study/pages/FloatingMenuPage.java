package com.example.study.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;

public class FloatingMenuPage {

    private static final String BASE_URL = "https://theinternet-tau.vercel.app/examples/floating-menu";
    private static final By PAGE_TITLE = By.xpath("//h1[normalize-space()='Floating Menu']");
    private static final By MENU_LINKS = By.cssSelector("nav a");

    private final WebDriver driver;
    private final WebDriverWait wait;

    public FloatingMenuPage(WebDriver driver, WebDriverWait wait) {
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

    public List<WebElement> getMenuLinks() {
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(MENU_LINKS));
    }

    public int getMenuLinkCount() {
        return getMenuLinks().size();
    }

    public void clickMenuLink(int index) {
        List<WebElement> links = getMenuLinks();
        links.get(index).click();
    }

    public boolean isMenuVisibleAfterScroll() {
        WebElement menu = wait.until(ExpectedConditions.visibilityOfElementLocated(MENU_LINKS));
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 500)");
        return wait.until(d -> menu.isDisplayed());
    }
}