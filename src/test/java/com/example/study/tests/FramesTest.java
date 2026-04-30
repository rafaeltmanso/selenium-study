package com.example.study.tests;

import com.example.study.base.BaseTest;
import com.example.study.pages.FramesPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FramesTest extends BaseTest {

    @Test
    public void testPageLoads() {
        FramesPage page = new FramesPage(driver, wait);
        page.open();
        
        Assertions.assertTrue(page.isPageLoaded(), "Page should load");
        Assertions.assertTrue(page.getIframe().isDisplayed(), "Iframe should be visible");
    }

    @Test
    public void testCanSwitchToIframeAndBack() {
        FramesPage page = new FramesPage(driver, wait);
        page.open();
        
        Assertions.assertEquals("Inside Iframe", page.getIframeContentText(), "Should see iframe content");
    }
}