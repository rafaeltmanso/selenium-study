package com.example.study.tests;

import com.example.study.base.BaseTest;
import com.example.study.pages.JsAlertsPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;

public class JsAlertsTest extends BaseTest {

    @Test
    public void testPageLoads() {
        JsAlertsPage page = new JsAlertsPage(driver, wait);
        page.open();
        
        Assertions.assertTrue(page.isPageLoaded(), "Page should load");
        Assertions.assertEquals(3, page.getAlertButtonCount(), "Should have 3 buttons");
    }

    @Test
    public void testAlertDialog() {
        JsAlertsPage page = new JsAlertsPage(driver, wait);
        page.open();
        
        page.clickAlertButton(0);
        
        Alert alert = page.getAlert();
        Assertions.assertNotNull(alert, "Alert should be present");
        
        alert.accept();
    }

    @Test
    public void testConfirmDialogAccept() {
        JsAlertsPage page = new JsAlertsPage(driver, wait);
        page.open();
        
        page.clickAlertButton(1);
        
        Alert alert = page.getAlert();
        alert.accept();
    }

    @Test
    public void testConfirmDialogDismiss() {
        JsAlertsPage page = new JsAlertsPage(driver, wait);
        page.open();
        
        page.clickAlertButton(1);
        
        Alert alert = page.getAlert();
        alert.dismiss();
    }

    @Test
    public void testPromptDialog() {
        JsAlertsPage page = new JsAlertsPage(driver, wait);
        page.open();
        
        page.clickAlertButton(2);
        
        Alert alert = page.getAlert();
        alert.sendKeys("Test Input");
        alert.accept();
    }
}