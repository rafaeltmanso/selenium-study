package com.example.study.tests;

import com.example.study.base.BaseTest;
import com.example.study.pages.HomePage;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePageTest extends BaseTest {

    @Override
    protected void beforeEach() {
        super.beforeEach();
    }

    @Test
    public void testPageTitle() {
        HomePage homePage = new HomePage(driver, wait);
        homePage.open();
        
        String title = homePage.getTitle();
        org.junit.jupiter.api.Assertions.assertTrue(title.contains("Rafael Manso"), 
            "Page title should contain 'Rafael Manso'");
    }

    @Test
    public void testContactButton() {
        HomePage homePage = new HomePage(driver, wait);
        homePage.open();
        
        homePage.clickContactButton();
        
        org.junit.jupiter.api.Assertions.assertTrue(homePage.isContactSectionVisible(),
            "Contact section should be visible after clicking button");
    }

    @Test
    public void testLinkedInButton() {
        HomePage homePage = new HomePage(driver, wait);
        homePage.open();
        
        String mainWindow = driver.getWindowHandle();
        homePage.clickLinkedInButton();
        
        String currentUrl = homePage.getCurrentUrlAfterLinkedInClick();
        org.junit.jupiter.api.Assertions.assertTrue(currentUrl.contains("linkedin.com"),
            "Should navigate to LinkedIn");
        
        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(mainWindow)) {
                driver.switchTo().window(handle);
                driver.close();
                break;
            }
        }
        driver.switchTo().window(mainWindow);
    }

    @Test
    public void testGitHubButton() {
        HomePage homePage = new HomePage(driver, wait);
        homePage.open();
        
        String mainWindow = driver.getWindowHandle();
        homePage.clickGitHubButton();
        
        String currentUrl = homePage.getCurrentUrlAfterGitHubClick();
        org.junit.jupiter.api.Assertions.assertTrue(currentUrl.contains("github.com"),
            "Should navigate to GitHub");
        
        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(mainWindow)) {
                driver.switchTo().window(handle);
                driver.close();
                break;
            }
        }
        driver.switchTo().window(mainWindow);
    }

    @Test
    public void testCvButton() {
        HomePage homePage = new HomePage(driver, wait);
        homePage.open();
        
        String mainWindow = driver.getWindowHandle();
        homePage.clickCvButton();
        
        String currentUrl = homePage.getCurrentUrlAfterCvClick();
        org.junit.jupiter.api.Assertions.assertTrue(currentUrl.contains("drive.google.com"),
            "Should navigate to Google Drive");
        
        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(mainWindow)) {
                driver.switchTo().window(handle);
                driver.close();
                break;
            }
        }
        driver.switchTo().window(mainWindow);
    }
}