package com.haman.lessons.core.pageObject.page;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class MainPage {

    @FindBy(css = "#newtodo")
    private SelenideElement addTask;

    @FindBy(css = "#adddivider")
    private SelenideElement addCategory;

    @FindBy(css = "#addlist")
    private SelenideElement addList;

    @FindBy(css = "#updatebox") // Maybe this thing should be with method in some BasePage.class or something
    private SelenideElement addElementName;  // which will be super class of all "page" classes

    public void createNewTask(String name) {
        addTask.val(name).pressEnter();
    }

    public void createNewCategory(String name) {
        addCategory.click();
        addElementName.val(name).pressEnter();
    }

    public void createNewList(String name) {
        addList.click();
        addElementName.val(name).pressEnter();
    }
}
