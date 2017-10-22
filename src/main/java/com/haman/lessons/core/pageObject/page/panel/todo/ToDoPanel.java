package com.haman.lessons.core.pageObject.page.panel.todo;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.haman.lessons.core.pageObject.page.MainPage;
import com.haman.lessons.core.pageObject.page.panel.AbstractPanel;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;

import java.util.LinkedList;

import static com.codeborne.selenide.Condition.text;

public class ToDoPanel extends AbstractPanel implements ToDoPanelable {

    @FindBy(css = "#newtodo")
    private SelenideElement addTask;
    @FindBy(xpath = "//ul[@id='mytodos']/li")
    private ElementsCollection toDos;
    @FindBy(xpath = "//img[@id='sortbutton']")
    private SelenideElement sortButton;
    @FindBy(xpath = "//a[@id='sort0']")
    private SelenideElement normal;
    @FindBy(xpath = "//a[@id='sort1']")
    private SelenideElement alphabetical;
    @FindBy(xpath = "//a[@id='sort2']")
    private SelenideElement random;
    @FindBy(xpath = "//a[@id='sort3']")
    private SelenideElement firstThree;

    public ToDoPanel(MainPage mainPage) {
        super(mainPage);
    }

    @Override
    public SelenideElement getElementByName(String taskName) {
        return toDos.filter(text(taskName)).shouldHaveSize(1).first();
    }

    @Step("Creating task: {name}")
    public void createTask(String name) {
        addTask.val(name).pressEnter();
    }

    @Override
    @Step("Completing Task in basic ToDos: {taskName}")
    public void completeTask(String taskName) {
        getElementByName(taskName).$("input").click();
    }

    @Step("Normal Sort")
    public LinkedList<String> normalSort() {
        sortButton.hover();
        normal.click();
        return new LinkedList<>(toDos.texts());
    }

    @Step("Alphabetical Sort")
    public LinkedList<String> alphabeticalSort() {
        sortButton.hover();
        alphabetical.click();
        return new LinkedList<>(toDos.texts());
    }

    @Step("Random Sort")
    public LinkedList<String> randomSort() {
        sortButton.hover();
        random.click();
        return new LinkedList<>(toDos.texts());
    }

    @Step("Display First Three Tasks")
    public ElementsCollection firstThree() {
        sortButton.hover();
        firstThree.click();
        return toDos;
    }

}
