package com.example.study.tests;

import com.example.study.base.BaseTest;
import com.example.study.pages.HoversPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HoversTest extends BaseTest {

    @Test
    public void testPageLoads() {
        HoversPage page = new HoversPage(driver, wait);
        page.open();
        
        Assertions.assertTrue(page.isPageLoaded(), "Page should load");
        Assertions.assertEquals(3, page.getCardCount(), "Should have 3 cards");
    }

    @Test
    public void testHoverRevealsUserInfo() {
        HoversPage page = new HoversPage(driver, wait);
        page.open();
        
        String userInfo = page.getCardUserInfo(0);
        Assertions.assertTrue(userInfo.contains("name:"), "Should reveal user info on hover");
    }
}