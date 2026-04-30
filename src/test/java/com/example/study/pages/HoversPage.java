package com.example.study.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;

public class HoversPage {

    private static final String BASE_URL = "https://theinternet-tau.vercel.app/examples/hovers";
    private static final By PAGE_TITLE = By.xpath("//h1[normalize-space()='Hovers']");
    private static final By CARDS = By.cssSelector("main .group.relative");

    private final WebDriver driver;
    private final WebDriverWait wait;

    public HoversPage(WebDriver driver, WebDriverWait wait) {
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

    public List<WebElement> getCards() {
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(CARDS));
    }

    public int getCardCount() {
        return getCards().size();
    }

    public void hoverOverCard(int index) {
        List<WebElement> cards = getCards();
        WebElement card = cards.get(index);
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].classList.add('group-hover'); arguments[0].querySelector('.absolute.inset-0').style.opacity = '1';",
                card);
    }

    public String getCardUserInfo(int index) {
        List<WebElement> cards = getCards();
        WebElement card = cards.get(index);
        hoverOverCard(index);
        WebElement userInfo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".absolute.inset-0 h3")));
        return userInfo.getText().trim();
    }
}