package com.haman.lessons.core.pageObject.page.panel;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.SelenidePageFactory;
import com.codeborne.selenide.impl.SelenideFieldDecorator;
import com.haman.lessons.core.pageObject.page.MainPage;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public abstract class AbstractPanel {

    @FindBy(css = "#updatebox")
    protected SelenideElement addElementName;

    protected MainPage mainPage;

    public AbstractPanel(MainPage mainPage) {
        this.mainPage = mainPage;
        SelenidePageFactory.initElements(new SelenideFieldDecorator(getWebDriver()), this);
    }

}
