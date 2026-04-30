package com.example.study.tests;

import com.example.study.base.BaseTest;
import com.example.study.pages.FormAuthPage;
import org.junit.jupiter.api.Test;

public class FormAuthTest extends BaseTest {

    private static final String USERNAME = "tomsmith";
    private static final String PASSWORD = "SuperSecretPassword!";

    @Test
    public void testSuccessfulLogin() {
        FormAuthPage page = new FormAuthPage(driver, wait);
        page.open();
        page.login(USERNAME, PASSWORD);
        
        org.junit.jupiter.api.Assertions.assertTrue(page.isSecureAreaLoaded(), 
            "Secure Area title should be displayed after login");
        org.junit.jupiter.api.Assertions.assertTrue(page.containsSuccessMessage(), 
            "Success message should be displayed after login");
        org.junit.jupiter.api.Assertions.assertTrue(page.isLogoutButtonVisible(), 
            "Logout button should be visible after login");
    }
}