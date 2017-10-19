package com.haman.lessons.core.pageObject.panel.listPanel;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Condition.text;

public class ListManagerPanel implements ListManagerPanelable {

    @FindBy(xpath = "//div[@id='lists']/ul/li/*/*")
    private ElementsCollection lists;

    @Override   //returns list or category
    public SelenideElement getNodeByName(String name) {
        return lists.filter(text(name)).shouldHaveSize(1).first();
    }

    @Override
    @Step("Moving List: {listName} to Category: {categoryName}")
    public void moveListToCategory(String listName, String categoryName) {
        getNodeByName(listName).dragAndDropTo(getNodeByName(categoryName).$x("../../ul"));
    }

    @Step("Switching to List: {listName}")
    public void goToList(String listName) {
        getNodeByName(listName).$x("span").click();
    }

    public String containingCategory(String listName) {
        return getNodeByName(listName).$x("../../h5/span").getText();
    }

}


