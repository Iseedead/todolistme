package com.haman.lessons;

import com.codeborne.selenide.Configuration;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import static com.codeborne.selenide.Selenide.close;
import static com.codeborne.selenide.Selenide.open;

public abstract class BaseTest {

    @BeforeMethod(description = "Init Chrome, go to todolistme.net")
    public void preCondition() {
        Configuration.browser = "chrome";
        Configuration.screenshots = false;
        open("http://todolistme.net/");
    }

    @AfterMethod(description = "Close Chrome Window")
    public void postCondition() {
        close();
    }
}
