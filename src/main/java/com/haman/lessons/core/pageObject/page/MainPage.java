package com.haman.lessons.core.pageObject.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.haman.lessons.core.pageObject.panel.taskPanel.ToDoPanel;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import java.util.LinkedList;

import static com.codeborne.selenide.Selenide.page;

public class MainPage {

    @FindBy(css = "#newtodo") private SelenideElement addTask;
    @FindBy(css = "#adddivider") private SelenideElement addCategory;
    @FindBy(css = "#addlist") private SelenideElement addList;

    @FindBy(xpath = "//img[@id='sortbutton']") private SelenideElement sortButton;
    @FindBy(xpath = "//a[@id='sort0']") private SelenideElement normal;
    @FindBy(xpath = "//a[@id='sort1']") private SelenideElement alphabetical;
    @FindBy(xpath = "//a[@id='sort2']") private SelenideElement random;
    @FindBy(xpath = "//a[@id='sort3']") private SelenideElement firstThree;

    @FindBy(css = "#updatebox") private SelenideElement addElementName;

    @Step("Creating task: {name}")
    public void createNewTask(String name) {
        addTask.val(name).pressEnter();
    }

    @Step("Creating Category: {name}")
    public void createNewCategory(String name) {
        addCategory.click();
        addElementName.val(name).pressEnter();
    }

    @Step("Creating List: {name}")
    public void createNewList(String name) {
        addList.click();
        addElementName.val(name).pressEnter();
    }

    @Step("Normal Sort")
    public LinkedList<String> normalSort() {
        sortButton.hover();
        normal.click();
        return page(ToDoPanel.class).getToDosText();
    }

    @Step("Alphabetical Sort")
    public LinkedList<String> alphabeticalSort() {
        sortButton.hover();
        alphabetical.click();
        return page(ToDoPanel.class).getToDosText();
    }

    @Step("Random Sort")
    public LinkedList<String> randomSort() {
        sortButton.hover();
        random.click();
        return page(ToDoPanel.class).getToDosText();
    }

    @Step("Display First Three Tasks")
    public ElementsCollection firstThree() {
        sortButton.hover();
        firstThree.click();
        return page(ToDoPanel.class).getToDos();
    }
}
