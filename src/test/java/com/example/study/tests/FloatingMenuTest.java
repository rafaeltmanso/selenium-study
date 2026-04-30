package com.example.study.tests;

import com.example.study.base.BaseTest;
import com.example.study.pages.FloatingMenuPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FloatingMenuTest extends BaseTest {

    @Test
    public void testPageLoads() {
        FloatingMenuPage page = new FloatingMenuPage(driver, wait);
        page.open();
        
        Assertions.assertTrue(page.isPageLoaded(), "Page should load");
        Assertions.assertEquals(5, page.getMenuLinkCount(), "Menu should have 5 links");
    }

    @Test
    public void testMenuVisibleAfterScroll() {
        FloatingMenuPage page = new FloatingMenuPage(driver, wait);
        page.open();
        
        Assertions.assertTrue(page.isMenuVisibleAfterScroll(), "Menu should remain visible after scroll");
    }
}