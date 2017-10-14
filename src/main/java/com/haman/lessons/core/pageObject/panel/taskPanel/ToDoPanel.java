package com.haman.lessons.core.pageObject.panel.taskPanel;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.haman.lessons.core.pageObject.panel.listPanel.ListManagerPanel;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.page;

public class ToDoPanel implements ToDoListPanelable {

    @FindBy(xpath = "//ul[@id='mytodos']/li")
    private ElementsCollection toDos;

    @Override
    public SelenideElement getToDoByName(String taskName) {
        return toDos.filter(text(taskName)).shouldHaveSize(1).first();
    }

    public void completeTask(String taskName) {
        getToDoByName(taskName).$("input").click();
    }

    // This is awkward, hope there is another method to get another panel.class method
    public void moveToList(String taskName, String listName) {
        getToDoByName(taskName).dragAndDropTo(page(ListManagerPanel.class).getNodeByName(listName));
    }
}
