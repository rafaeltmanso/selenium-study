package com.example.study.tests;

import com.example.study.base.BaseTest;
import com.example.study.pages.DynamicLoadingPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DynamicLoadingTest extends BaseTest {

    @Test
    public void testContentAppearsAfterDelay() {
        DynamicLoadingPage page = new DynamicLoadingPage(driver, wait);
        page.open();
        
        page.clickStartButton();
        
        Assertions.assertTrue(page.isContentLoaded(), "Content should appear after start button clicked");
    }
}