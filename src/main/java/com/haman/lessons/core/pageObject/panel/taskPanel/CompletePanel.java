package com.haman.lessons.core.pageObject.panel.taskPanel;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Condition.text;

public class CompletePanel implements ToDoListPanelable {

    @FindBy(xpath = "//ul[@id='mydonetodos']/li")
    private ElementsCollection completes;

    public ElementsCollection getCompletes() {
        return completes;
    }

    @Override
    public SelenideElement getToDoByName(String name) {
        return completes.filter(text(name)).shouldHaveSize(1).first();
    }
}
