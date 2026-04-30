package com.example.study.tests;

import com.example.study.base.BaseTest;
import com.example.study.pages.MultipleWindowsPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MultipleWindowsTest extends BaseTest {

    @Test
    public void testPageLoads() {
        MultipleWindowsPage page = new MultipleWindowsPage(driver, wait);
        page.open();
        
        Assertions.assertTrue(page.isPageLoaded(), "Page should load");
        Assertions.assertTrue(page.getOpenButton().isDisplayed(), "Open button should be visible");
    }

    @Test
    public void testCanOpenNewWindow() {
        MultipleWindowsPage page = new MultipleWindowsPage(driver, wait);
        page.open();
        
        String mainWindow = driver.getWindowHandle();
        page.clickOpenButton();
        
        page.switchToNewWindow();
        
        Assertions.assertNotEquals(mainWindow, driver.getWindowHandle(), "Should be in new window");
        
        page.closeAndSwitchBack();
        
        Assertions.assertEquals(mainWindow, driver.getWindowHandle(), "Should be back in main window");
    }
}