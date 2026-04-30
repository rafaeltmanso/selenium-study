package com.example.study.tests;

import com.example.study.base.BaseTest;
import com.example.study.pages.EntryAdPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EntryAdTest extends BaseTest {

    @Test
    public void testPageLoads() {
        EntryAdPage page = new EntryAdPage(driver, wait);
        page.open();
        
        Assertions.assertTrue(page.isPageLoaded(), "Page should load");
        Assertions.assertTrue(page.getDescription().contains("modal appears"), 
            "Description should mention modal");
    }

    @Test
    public void testModalAppearsAndCanBeClosed() {
        EntryAdPage page = new EntryAdPage(driver, wait);
        page.open();
        
        Assertions.assertTrue(page.isModalVisible(), "Modal should appear");
        
        page.clickCloseButton();
        
        Assertions.assertTrue(page.isModalDismissed(), "Modal should be dismissed");
    }
}