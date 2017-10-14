package com.haman.lessons;

import com.codeborne.selenide.Configuration;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import static com.codeborne.selenide.Selenide.close;
import static com.codeborne.selenide.Selenide.open;

public abstract class BaseTest {

    @BeforeMethod
    public void preCondition() {
        Configuration.browser = "chrome";
        open("http://todolistme.net/");
    }

    @AfterMethod
    public void postCondition() {
        close();
    }
}
