package com.example.study.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

    private static final String BASE_URL = "https://rafaelmanso.dev";
    private static final By CONTACT_SECTION = By.id("contact");
    private static final By CONTACT_BUTTON = By.cssSelector("a[href='#contact']");
    private static final By LINKEDIN_BUTTON = By.cssSelector("a[href*='linkedin.com']");
    private static final By GITHUB_BUTTON = By.cssSelector("a[href*='github.com']");
    private static final By CV_BUTTON = By.cssSelector("a[href*='drive.google.com']");

    private final WebDriver driver;
    private final WebDriverWait wait;

    public HomePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void open() {
        driver.get(BASE_URL);
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public void clickContactButton() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(CONTACT_BUTTON));
        button.click();
    }

    public boolean isContactSectionVisible() {
        WebElement section = wait.until(ExpectedConditions.visibilityOfElementLocated(CONTACT_SECTION));
        return section != null;
    }

    public void clickLinkedInButton() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(LINKEDIN_BUTTON));
        button.click();
        switchToNewWindow();
    }

    public String getCurrentUrlAfterLinkedInClick() {
        wait.until(ExpectedConditions.urlContains("linkedin.com"));
        return driver.getCurrentUrl();
    }

    public void clickGitHubButton() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(GITHUB_BUTTON));
        button.click();
        switchToNewWindow();
    }

    public String getCurrentUrlAfterGitHubClick() {
        wait.until(ExpectedConditions.urlContains("github.com"));
        return driver.getCurrentUrl();
    }

    public void clickCvButton() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(CV_BUTTON));
        button.click();
        switchToNewWindow();
    }

    public String getCurrentUrlAfterCvClick() {
        wait.until(ExpectedConditions.urlContains("drive.google.com"));
        return driver.getCurrentUrl();
    }

    private void switchToNewWindow() {
        String originalWindow = driver.getWindowHandle();
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(originalWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
    }

    public void closeNewWindowAndSwitchBack() {
        String currentWindow = driver.getWindowHandle();
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(currentWindow)) {
                driver.switchTo().window(windowHandle);
                driver.close();
                break;
            }
        }
    }
}