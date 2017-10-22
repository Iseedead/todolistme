package com.haman.lessons.core.pageObject.page.panel.list;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.haman.lessons.core.pageObject.page.MainPage;
import com.haman.lessons.core.pageObject.page.panel.AbstractPanel;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Condition.text;

public class ListPanel extends AbstractPanel implements ListPanelable {

    @FindBy(css = "#adddivider")
    private SelenideElement addCategory;
    @FindBy(css = "#addlist")
    private SelenideElement addList;

    @FindBy(xpath = "//div[@id='lists']/ul/li/*/*")
    private ElementsCollection lists;

    public ListPanel(MainPage mainPage) {
        super(mainPage);
    }

    @Step("Creating Category: {name}")
    public void createNewCategory(String categoryName) {
        addCategory.click();
        addElementName.val(categoryName).pressEnter();
    }

    @Override
    public SelenideElement getElementByName(String name) {
        return lists.filter(text(name)).shouldHaveSize(1).first();
    }

    @Step("Creating List: {name}")
    @Override
    public void createNewList(String listName) {
        addList.click();
        addElementName.val(listName).pressEnter();
    }

    @Step("Moving List: {listName} to Category: {categoryName}")
    public void moveListToCategory(String listName, String categoryName) {
        getElementByName(listName).dragAndDropTo(getElementByName(categoryName).$x("../../ul"));
    }

    @Step("Move Task: {taskName} to List: {listName}")
    public void moveToList(String taskName, String listName) {
        mainPage.getToDoPanel().getElementByName(taskName).dragAndDropTo(getElementByName(listName));
    }

    @Step("Switching to List: {listName}")
    public void goToList(String listName) {
        getElementByName(listName).$x("span").click();
    }

    public String containingCategory(String listName) {
        return getElementByName(listName).$x("../../h5/span").getText();
    }

}


